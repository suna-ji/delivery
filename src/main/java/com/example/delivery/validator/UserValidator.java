package com.example.delivery.validator;

import com.example.delivery.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final DeliveryValidatorUtils deliveryValidatorUtils;
    private final MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public void validateAdd(User user, BindingResult bindingResult) {
        // 필수정보 (ID, 비밀번호, 사용자 이름) 확인
        deliveryValidatorUtils.rejectIfEmptyOrWhitespace(bindingResult, "userId");
        deliveryValidatorUtils.rejectIfEmptyOrWhitespace(bindingResult, "password");
        deliveryValidatorUtils.rejectIfEmptyOrWhitespace(bindingResult, "userName");
        // 비밀번호 조건 확인 (영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상으로 12자리 이상)
        deliveryValidatorUtils.rejectIfInvalidPassword(bindingResult, "password");
    }

    public void validateLogin(User user, BindingResult bindingResult) {
        // 필수정보 (ID, 비밀번호) 확인
        deliveryValidatorUtils.rejectIfEmptyOrWhitespace(bindingResult, "userId");
        deliveryValidatorUtils.rejectIfEmptyOrWhitespace(bindingResult, "password");
    }
}
