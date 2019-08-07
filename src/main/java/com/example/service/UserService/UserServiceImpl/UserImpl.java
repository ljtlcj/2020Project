package com.example.service.UserService.UserServiceImpl;

import com.example.annotation.ReadDataSource;
import com.example.annotation.WriteDataSource;
import com.example.domain.User;
import com.example.domain.UserTable;
import com.example.mapper.UserMapper;
import com.example.mapper.UserTableMapper;

import com.example.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserImpl")
public class UserImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserTableMapper userTableMapper;

    @Override
    @ReadDataSource
    public User Login(int id) {
        User user1 = userMapper.selectByPrimaryKey(id);
        return user1;
    }


    @Override
    @ReadDataSource
    public UserTable GetUserTable(int id) {
        UserTable userTable = userTableMapper.selectByPrimaryKey(id);
        return userTable;
    }

    @Override
    @WriteDataSource
    public int AddUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }
}
