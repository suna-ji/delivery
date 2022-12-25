package com.example.delivery.validator;

import com.example.delivery.dto.searchoption.DeliverySearchOption;
import com.example.delivery.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class DeliveryValidator implements Validator {

    private final DeliveryValidatorUtils deliveryValidatorUtils;
    private final MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public void validateSelect(DeliverySearchOption searchOption, Errors errors) {
        // 조회 정보 들어왔는지 확인
        deliveryValidatorUtils.rejectIfEmptyOrWhitespace(errors, "fromDateTime");
        deliveryValidatorUtils.rejectIfEmptyOrWhitespace(errors, "toDateTime");
        if(searchOption.getUserId() == null || !StringUtils.hasText(searchOption.getUserId())|| !StringUtils.hasLength(searchOption.getUserId().trim())) {
            errors.rejectValue("userId", ErrorCode.FAIL_TO_GET_CURRENT_USER_ID.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.FAIL_TO_GET_CURRENT_USER_ID.getErrorCode()), null, Locale.getDefault()));
        }
        // from날짜가 to날짜보다 이전인지 확인 (from ~ to)
        if (!searchOption.getFromDateTime().before(searchOption.getToDateTime())) {
            errors.rejectValue("fromDateTime", ErrorCode.FROM_DATE_IS_NOT_BEFORE_THE_TO_DATE.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.FROM_DATE_IS_NOT_BEFORE_THE_TO_DATE.getErrorCode()), null, Locale.getDefault()));
        }
        // from날짜와 to날짜의 간격이 3일이내인지 확인
        Date toDate =  new Date(searchOption.getToDateTime().getTime());
        Date fromDate =  new Date(searchOption.getFromDateTime().getTime());
        long diffSec = (toDate.getTime() - fromDate.getTime()) / 1000; //초 차이
        long diffDays = diffSec / (24*60*60); //일자수 차이
        if (diffDays > 3) {
            errors.rejectValue("fromDateTime", ErrorCode.DELIVERY_SEARCH_PERIOD_HAS_EXCEEDED_3_DAYS.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.DELIVERY_SEARCH_PERIOD_HAS_EXCEEDED_3_DAYS.getErrorCode()), null, Locale.getDefault()));
        }
    }
}
