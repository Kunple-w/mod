package com.example.mod.api.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author wangyongxu
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Component
// TODO: 2021-07-08 12:35:56 暂时依赖Component by wangyongxu
public @interface ModService {
}
