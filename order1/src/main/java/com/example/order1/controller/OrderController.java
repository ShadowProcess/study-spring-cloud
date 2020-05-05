package com.example.order1.controller;

import com.example.order1.converter.OrderForm2OrderDtoConverter;
import com.example.order1.dto.OrderDto;
import com.example.order1.enums.ResultEnum;
import com.example.order1.exception.OrderException;
import com.example.order1.from.OrderFrom;
import com.example.order1.service.OrderService;
import com.example.order1.utils.ResultVoUtil;
import com.example.order1.vo.ResultVo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Setter(onMethod = @__(@Autowired))
    private OrderService orderService;

    /**
     1.参数校验
     2.查询商品信息（调用商品服务）
     3.计算总价
     4.扣库存（调用商品服务）
     5.订单入库
     */
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderFrom orderFrom, BindingResult bindingResult){

        //参数校验
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderFrom={}",orderFrom);
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        // orderFrom -> OrderDto
        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderFrom);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDto result = orderService.create(orderDto);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVoUtil.success(map);
    }

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @PostMapping("/finish")
    public ResultVo<OrderDto> finish(@RequestParam("orderId") String orderId){
        return ResultVoUtil.success(orderService.finish(orderId));
    }

}
