package com.example.zuul.filter;

import com.example.zuul.constant.RedisConstant;
import com.example.zuul.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

/**
 * 模拟Zuul的权限校验
 * 区分卖家和卖家
 */
public class AuthSellerFilter extends ZuulFilter {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        if ("/order/order/finish".equals(request.getRequestURI())) {
            return true;
        }

        return false;
    }

    /**
     * zuul权限校验---不同的人只能访问不同的URL
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

           /*
           /order/finish 只能卖家访问（cookie里有token,并且对应redis中的值）   【GET login/seller】
           */

        Cookie cookie = CookieUtil.get(request, "token");
        if (cookie == null
                || StringUtils.isEmpty(cookie.getValue())
                || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {

            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }
}
