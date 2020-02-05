package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

/**
 * 权限拦截 （区分买家和卖家）
 */
public class PermissionFilter extends ZuulFilter {
    /**
     * filterType
     *  返回一个代表过滤器的类型，在Zuul中定义了四种不同生命周期的过滤类型
     *  pre:路由之前
     *  routing:路由之时
     *  post:路由之后
     *  error:发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 值越小越优先
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
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //这里从url参数里获取，也可以从cookie，Header里边获取
        String token = request.getParameter("token");

        if (StringUtils.isEmpty(token)) {
            //requestContext.setSendZuulResponse(false); //表示不通过
            //requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
