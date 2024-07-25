package com.igriss.AkkSell.mappers.mapper_helper;

import com.igriss.AkkSell.entities.User;
import com.igriss.AkkSell.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperHelper {

    private final UserService userService;

    @Autowired
    public UserMapperHelper(UserService userService) {
        this.userService = userService;
    }

    public User userIdToUser(Long userId) {
        return userService.findById(userId);
    }

    public Long userToUserId(User user) {
        return user.getId();
    }
}
