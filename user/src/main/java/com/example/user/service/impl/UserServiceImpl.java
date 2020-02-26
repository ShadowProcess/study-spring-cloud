package com.example.user.service.impl;

import com.example.user.dataobject.UserInfo;
import com.example.user.repository.UserInfoRepository;
import com.example.user.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Setter(onMethod = @__(@Autowired))
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }
}
