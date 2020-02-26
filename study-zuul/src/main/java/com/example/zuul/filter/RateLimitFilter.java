package com.example.zuul.filter;

import com.example.zuul.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 模拟Zuul限流
 */
//@Component
public class RateLimitFilter extends ZuulFilter {

    //令牌桶
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100); //每秒放几个令牌

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        //RATE_LIMITER.tryAcquire() 这个方法表示去令牌桶,取一个令牌
        if (!RATE_LIMITER.tryAcquire()) {

            //如果取不到
            throw new RateLimitException();
        }

        return null;
    }
}
