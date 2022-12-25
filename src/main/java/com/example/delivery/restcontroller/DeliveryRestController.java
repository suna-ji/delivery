package com.example.delivery.restcontroller;

import com.example.delivery.dto.Delivery;
import com.example.delivery.dto.searchoption.DeliverySearchOption;
import com.example.delivery.security.SecurityUtil;
import com.example.delivery.service.DeliveryService;
import com.example.delivery.validator.DeliveryValidator;
import com.example.delivery.validator.DeliveryValidatorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * 베달 REST Controller
 */
@RestController
@RequestMapping("/api/{version}/delivery")
@RequiredArgsConstructor
public class DeliveryRestController extends BaseRestController {

    private final DeliveryService deliveryService;
    private final DeliveryValidator deliveryValidator;
    /**
     * 배달 조회 API
     * 기간내에 사용자가 주문한 배달의 리스트를 제공한다.
     */
    @PostMapping("")
    public ResponseEntity list(@PathVariable String version, @RequestBody DeliverySearchOption searchOption, BindingResult bindingResult) {
        String userId = SecurityUtil.getCurrentMemberId();
        searchOption.setUserId(userId);
        deliveryValidator.validateSelect(searchOption, bindingResult);
        if (bindingResult.hasErrors()) {
            return list(searchOption, bindingResult);
        }
        return list(deliveryService.findByDate(searchOption));
    }

}
