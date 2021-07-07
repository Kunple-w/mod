package com.example.mod.api.annotation;

import java.lang.annotation.*;

/**
 * @author wangyongxu
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModService {
}
