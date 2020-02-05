package com.example.user.service;

import com.example.user.dataobject.UserInfo;

public interface UserService {

    UserInfo findByOpenid(String openid);
}
