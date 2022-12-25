package com.example.delivery.restcontroller;

import com.example.delivery.dto.TokenInfo;
import com.example.delivery.dto.User;
import com.example.delivery.exception.ErrorCode;
import com.example.delivery.service.UserService;
import com.example.delivery.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * 사용자 REST Controller
 */
@RestController
@RequestMapping("/api/{version}/user")
@RequiredArgsConstructor
public class UserRestController extends BaseRestController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final MessageSource messageSource;

    /**
     * 회원가입 API
     */
    @PostMapping("")
    public ResponseEntity add(@PathVariable String version, @RequestBody User user, BindingResult bindingResult) {
        userValidator.validateAdd(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return add(user, bindingResult);
        }
        int count = userService.add(user);
        return add(count, userService.findById(user.getUserId()));
    }

    /**
     * 로그인 API
     */
    @PostMapping("/login")
    public ResponseEntity login(@PathVariable String version, @RequestBody User user, BindingResult bindingResult) {
        userValidator.validateLogin(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return login(user, bindingResult);
        }
        TokenInfo tokenInfo = null;
        try {
            tokenInfo = userService.login(user.getUserId(), user.getPassword());
        } catch (Exception e) {
            bindingResult.rejectValue("userId", ErrorCode.LOGIN_FAIL.toString(), null, messageSource.getMessage(String.valueOf(ErrorCode.LOGIN_FAIL.getErrorCode()), null, Locale.getDefault()));
            return login(user, bindingResult);
        }
        return login(tokenInfo);
    }

}
