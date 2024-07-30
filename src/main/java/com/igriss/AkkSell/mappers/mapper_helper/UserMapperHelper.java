package com.igriss.AkkSell.mappers.mapper_helper;

import com.igriss.AkkSell.entities.User;
import com.igriss.AkkSell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperHelper {

    private final UserService userService;

    public User userIdToUser(Long userId) {
        return userService.findById(userId);
    }
}
