package com.example.order.service.impl;

import com.example.order.client.ProductClient;
import com.example.order.dataobject.OrderDetail;
import com.example.order.dataobject.OrderMaster;
import com.example.order.dataobject.ProductInfo;
import com.example.order.dto.CartDto;
import com.example.order.dto.OrderDto;
import com.example.order.enums.OrderStatus;
import com.example.order.enums.PayStatus;
import com.example.order.enums.ResultEnum;
import com.example.order.exception.OrderException;
import com.example.order.repository.OrderDetailRepository;
import com.example.order.repository.OrderMasterRepository;
import com.example.order.service.OrderService;
import com.example.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource //byName注入
    private OrderDetailRepository orderDetailRepository;
    @Resource //byName注入
    private OrderMasterRepository orderMasterRepository;

    @Autowired //byType注入
    private ProductClient productClient;

    /**
     * 1.Spring的BeanUtils的CopyProperties方法需要对应的属性有getter和setter方法；
     * 2.如果存在属性完全相同的内部类，但是不是同一个内部类，即分别属于各自的内部类，则spring会认为属性不同，不会copy；
     * <p>
     * org.springframework.beans.BeanUtils.copyProperties(Object source,Object target),
     * 作用就是把两个对象中相同字段进行赋值。不一定是相同对象，只要两个对象中有相同的成员变量就可以赋值。
     */
    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey();


        //TODO 查询商品信息（调用商品服务）
        List<String> productIdList = orderDto.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);

        //读redis
        //减库存并将新值重新设置进redis

        //订单入库异常，手动回滚redis

        /**
         * 异步扣库存分析
         * 1.库存在Redis中保存
         * 2.收到请求Redis判断是否库存充足，减掉Redis中库存
         * 3.订单服务创建订单写入数据库，并发送消息
         */


        //TODO 计算总价
        BigDecimal orderAmount = BigDecimal.ZERO;
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);

                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetail.setCreateTime(new Date());
                    orderDetail.setUpdateTime(new Date());

                    //TODO 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //TODO 扣库存（调用商品服务）
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDtoList);


        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMasterRepository.save(orderMaster);
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(String orderId) {
        //1.先查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw  new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }

        //2.判断订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (OrderStatus.NEW.getCode() != orderMaster.getOrderStatus()) {
            throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //3.修改订单状态为完结
        orderMaster.setOrderStatus(OrderStatus.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        //查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);

        return orderDto;
    }
}
