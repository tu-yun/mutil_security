package com.munhak.api.common.annotation;

import com.munhak.api.common.validator.EnumPatternValidator;
import com.munhak.api.enums.RegExpType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {EnumPatternValidator.class})
public @interface EnumPattern {
    RegExpType regExpType();
    String message() default "정규식이 정의되지 않았습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String name() default "";
    boolean required() default true;
}
