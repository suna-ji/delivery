package com.example.delivery.restcontroller;

import com.example.delivery.dto.User;
import com.example.delivery.service.UserService;
import com.example.delivery.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 REST Controller
 */
@RestController
@RequestMapping("/api/{version}/user")
@RequiredArgsConstructor
public class UserRestController extends BaseRestController {

    private final UserService userService;
    private final UserValidator userValidator;

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
            return add(user, bindingResult);
        }
        int count = userService.add(user);
        return add(count, userService.findById(user.getUserId()));
    }

}
