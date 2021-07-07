package com.example.mod.demo.provider.service;

import com.example.mod.api.annotation.ModInject;
import com.example.mod.demo.distribute.app1.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangyongxu
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @ModInject
    private EmailService emailService;

    @Override
    public String sayHi(String to, String msg) {
        logger.info("say hi: {}, msg: {}", to, msg);
        emailService.send(to, msg);
        return "ok";
    }
}
