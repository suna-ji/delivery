package com.example.delivery.validator;

import com.example.delivery.dto.Delivery;
import com.example.delivery.dto.Doro;
import com.example.delivery.dto.searchoption.DeliverySearchOption;
import com.example.delivery.exception.ErrorCode;
import com.example.delivery.mapper.DeliveryMapper;
import com.example.delivery.mapper.DoroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
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
    private final DeliveryMapper deliveryMapper;
    private final DoroMapper doroMapper;

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

    public Errors validateModify(Integer deliveryId, Doro doro) {
        // 배달 상태가 READY인지 확인
        Delivery delivery = deliveryMapper.selectById(deliveryId);
        if (delivery.getStatus() == null || !delivery.getStatus().equals("READY")) {
            Delivery city = Delivery.builder()
                    .deliveryId(deliveryId)
                    .build();
            Errors errors = new BeanPropertyBindingResult(delivery, "delivery");
            errors.rejectValue("status", ErrorCode.FAIL_TO_MODIFY_DELIVERY.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.FAIL_TO_MODIFY_DELIVERY.getErrorCode()), null, Locale.getDefault()));
            return errors;
        }
        // 도로명 주소가 올바른지 확인
        Integer doroId = doroMapper.selectDoroId(doro.getSidoId(), doro.getSigunguId(), doro.getDoroNameId(), doro.getBuildingNumber());
        if (doroId == null) {
            Errors errors = new BeanPropertyBindingResult(doro, "doro");
            errors.rejectValue("doroId", ErrorCode.INVALID_ADDRESS.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.INVALID_ADDRESS.getErrorCode()), null, Locale.getDefault()));
            return errors;
        }
        return null;
    }
}
