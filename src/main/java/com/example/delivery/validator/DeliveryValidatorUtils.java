package com.example.delivery.validator;

import com.example.delivery.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 공통 validator util
 */
@Component
@RequiredArgsConstructor
public class DeliveryValidatorUtils {

    private final MessageSource messageSource;

    // 비밀번호 패턴 (대문자, 소문자, 숫자, 특수문자의 중 3가지 조합으로 12자리이상)
    // 1. 소문자 숫자 특수문자
    private static final Pattern PATTERN_PW_1 = Pattern.compile("^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{12,}$");
    // 2. 대문자 숫자 특수문자
    private static final Pattern PATTERN_PW_2 = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{12,}$");
    // 3. 대문자 소문자 특수문자
    private static final Pattern PATTERN_PW_3 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{12,}$");
    // 4. 대문자 소문자 숫자
    private static final Pattern PATTERN_PW_4 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]{12,}$");

    public void rejectIfEmptyOrWhitespace(Errors errors, String field) {
        Object value = errors.getFieldValue(field);
        if(value == null || !StringUtils.hasText(value.toString())|| !StringUtils.hasLength(value.toString().trim())) {
            errors.rejectValue(field, ErrorCode.REQUIRED_FIELD_EMPTY.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.REQUIRED_FIELD_EMPTY.getErrorCode()), null, Locale.getDefault()));
        }
    }

    public void rejectIfInvalidPassword(Errors errors, String passwordField) {

        Object passwordObject = errors.getFieldValue(passwordField);
        if (!StringUtils.hasLength(String.valueOf(passwordObject)))
            return;

        String password = passwordObject.toString();

        if ((!PATTERN_PW_1.matcher(password).matches() && !PATTERN_PW_2.matcher(password).matches()  && !PATTERN_PW_3.matcher(password).matches() && !PATTERN_PW_4.matcher(password).matches()) || password.contains(" ")) {
            String errorCode = String.valueOf(ErrorCode.PASSWORD_PATTERN_NOT_MATCH.getErrorCode());
            errors.rejectValue("password", errorCode, null, messageSource.getMessage(errorCode, null, Locale.getDefault()));

        }
    }


}

