package com.example.elasticsearch.dao;

import com.example.elasticsearch.entry.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDao extends ElasticsearchRepository<User,Integer> {
}
