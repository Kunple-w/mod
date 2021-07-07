package com.example.mod.api.annotation;

import java.lang.annotation.*;

/**
 * @author wangyongxu
 */

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModInject {
}
