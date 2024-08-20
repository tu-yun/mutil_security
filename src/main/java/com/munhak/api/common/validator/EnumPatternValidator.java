package com.munhak.api.common.validator;

import com.munhak.api.common.annotation.EnumPattern;
import com.munhak.api.common.exception.CustomValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumPatternValidator implements ConstraintValidator<EnumPattern, String> {
    private Pattern pattern;
    private EnumPattern annotation;

    @Override
    public void initialize(EnumPattern annotation) {
        try {
            this.annotation = annotation;
            pattern = Pattern.compile(annotation.regExpType().regexp());
        } catch (PatternSyntaxException e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            throw new CustomValidationException(annotation.regExpType().message(), errorMap);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!annotation.required() && (value == null || value.isEmpty())) {
            return true;
        }
        if (value == null) {
            return false;
        }

        Matcher m = pattern.matcher(value);
        if (!m.matches()) {
            Map<String, String> errorMap = new HashMap<>();
            if (!annotation.name().isEmpty()) {
                errorMap.put("name", annotation.name());
            }
            errorMap.put("value", value);
            throw new CustomValidationException(annotation.regExpType().message(), errorMap);
        }
        return true;
    }
}
