package com.battcn.annotation;


import com.battcn.validator.DateTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Levin
 * @since 2018/6/6 0006
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = DateTimeValidator.class)
public @interface DateTime {

    /**
     * 错误消息  - 关键字段
     *
     * @return 默认错误消息
     */
    String message() default "格式错误";

    /**
     * 格式
     *
     * @return 验证的日期格式
     */
    String format() default "yyyy-MM-dd";

    /**
     * 允许我们为约束指定验证组 - 关键字段（TODO 下一章中会介绍）
     *
     * @return 分组
     */
    Class<?>[] groups() default {};

    /**
     * payload - 关键字段
     *
     * @return 暂时不清楚, 知道的欢迎留言交流
     */
    Class<? extends Payload>[] payload() default {};
}
