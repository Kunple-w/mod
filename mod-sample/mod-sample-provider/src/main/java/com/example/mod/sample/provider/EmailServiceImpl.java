package com.example.mod.sample.provider;

import com.example.mod.api.annotation.ModService;
import com.example.mod.sample.provider.api.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangyongxu
 */
@ModService
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public String send(String receiver, String msg) {
        logger.info("send msg: {} to : {}", msg, receiver);
        return "send msg: " + msg;
    }
}
