package com.example.user.controller;

import com.example.user.constant.CookieConstant;
import com.example.user.constant.RedisConstant;
import com.example.user.dataobject.UserInfo;
import com.example.user.enums.ResultEnum;
import com.example.user.enums.RoleEnum;
import com.example.user.service.UserService;
import com.example.user.utils.CookieUtil;
import com.example.user.utils.ResultVoUtil;
import com.example.user.vo.ResultVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;

    @Setter(onMethod = @__(@Autowired))
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVo buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        //1.openid和数据库的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }

        //2.判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }

        //3.设置cookie [cookie里设置openid=abc]
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);

        return ResultVoUtil.success();
    }

    @GetMapping("/seller")
    public ResultVo seller(@RequestParam("openid") String openid,
                           HttpServletResponse response,
                           HttpServletRequest request) {

        //判断是否登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null
                && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))) {

            return ResultVoUtil.success();
        }

        //1.openid和数据库里的数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null) {
            return ResultVoUtil.error(ResultEnum.LOGIN_FAIL);
        }

        //2.判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
            return ResultVoUtil.error(ResultEnum.ROLE_ERROR);
        }

        //3.redis设置 key=UUID value=xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),
                openid,
                expire,
                TimeUnit.SECONDS);

        //4.cookie里设置token=UUID
        CookieUtil.set(response,CookieConstant.TOKEN,token,CookieConstant.expire);

        return ResultVoUtil.success();
    }
}
