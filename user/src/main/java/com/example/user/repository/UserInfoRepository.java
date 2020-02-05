package com.example.user.repository;

import com.example.user.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    UserInfo findByOpenid(String openid);
}
