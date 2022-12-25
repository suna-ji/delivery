package com.example.delivery.restcontroller;

import com.example.delivery.dto.Delivery;
import com.example.delivery.dto.Doro;
import com.example.delivery.dto.searchoption.DeliverySearchOption;
import com.example.delivery.security.SecurityUtil;
import com.example.delivery.service.DeliveryService;
import com.example.delivery.validator.DeliveryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
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

    /**
     * 배달 수정 API
     * 사용자로부터 도착지 주소를 요청받아 처리한다.
     * 변경 가능한 배달의 경우에만 수정한다.
     */
    @PutMapping("/{deliveryId}")
    public ResponseEntity modify(@PathVariable String version, @PathVariable Integer deliveryId, @RequestBody Doro doro) {
        Errors errors = deliveryValidator.validateModify(deliveryId, doro);
        Delivery delivery = Delivery.builder().deliveryId(deliveryId).build();
        if (errors != null) {
            return modify(delivery, errors);
        }
        int count = deliveryService.modify(delivery, doro);
        return modify(count, deliveryService.findById(delivery.getDeliveryId()));
    }

}
