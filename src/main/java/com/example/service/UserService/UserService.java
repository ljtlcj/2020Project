package com.example.service.UserService;

import com.example.domain.User;
import com.example.domain.UserTable;
import org.springframework.stereotype.Service;


public interface UserService {
    User Login(int id);

    UserTable GetUserTable(int id);

    int AddUser(User user);
}
