package ukma.fi.scheduler.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ukma.fi.scheduler.ServiceMarker;
import ukma.fi.scheduler.controller.dto.UserLoginDTO;
import ukma.fi.scheduler.exceptionHandlers.exceptions.InvalidData;
import ukma.fi.scheduler.exceptionHandlers.exceptions.UserNotFoundException;
import ukma.fi.scheduler.repository.UserRepository;
import ukma.fi.scheduler.entities.*;
import ukma.fi.scheduler.service.*;

import java.util.Collections;

@ServiceMarker
@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

    //for searching users in DB
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(UserLoginDTO user) {
        User userInDb = userService.findUserByLogin(user.getLogin());
        if (userInDb == null) {
            throw new UserNotFoundException("User not found.");
        }
        if (!userInDb.getPassword().equals(user.getPassword())) {
            throw new InvalidData(Collections.singletonMap("password",user.getPassword()));
        }
        log.info("Login successfully -> " + user.getLogin());
        return userInDb;
    }

    //use naukma e-mail
    @Override
    public User registration(User user) {
        log.info("registration -> user:" + user.getLogin());
        return userRepository.save(user);
    }

    @Override
    public User getUserInfo(Long id) {
        User userInDb = userService.findUserById(id);
        if (userInDb == null) {
            throw new UserNotFoundException(id);
        }
        log.info("get user info -> user id:" + id);
        return userInDb;
    }

}
