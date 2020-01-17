package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

/**
 * 模拟Zuul的权限校验
 * 区分卖家和卖家
 */
public class AuthFilter extends ZuulFilter {
    /**
     * filterType
     * 返回一个代表过滤器的类型，在Zuul中定义了四种不同生命周期的过滤类型
     * pre:路由之前
     * routing:路由之时
     * post:路由之后
     * error:发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 值越小越优先
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

           /*
           /order/create 只能买家访问
           /order/finish 只能卖家访问
           /product/list 都可以访问
           */


        return null;
    }
}
