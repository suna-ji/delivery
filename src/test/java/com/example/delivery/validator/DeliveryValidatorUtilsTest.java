package com.example.delivery.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;


class DeliveryValidatorUtilsTest {

    // 1. 소문자 숫자 특수문자
    private static final Pattern PATTERN_PW_1 = Pattern.compile("^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{12,}$");
    // 2. 대문자 숫자 특수문자
    private static final Pattern PATTERN_PW_2 = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{12,}$");
    // 3. 대문자 소문자 특수문자
    private static final Pattern PATTERN_PW_3 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{12,}$");
    // 4. 대문자 소문자 숫자
    private static final Pattern PATTERN_PW_4 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]{12,}$");

    @Test
    void rejectIfInvalidPassword() {
        // given
        String invalidPassword1 = "a";
        String invalidPassword2 = "1111111111111111111111";
        String validPassword1= "abcdedf1234567!!!";
        String validPassword2= "ABCDEf1234567!!!";
        String validPassword3= "abcdeABCDEFG!!!@@@";


        // when
        boolean invalidResult1 = patternMatching(invalidPassword1);
        boolean invalidResult2 = patternMatching(invalidPassword2);
        boolean validResult1 = patternMatching(validPassword1);
        boolean validResult2 = patternMatching(validPassword2);
        boolean validResult3 = patternMatching(validPassword3);

        // then
        assertFalse(invalidResult1);
        assertFalse(invalidResult2);
        assertTrue(validResult1);
        assertTrue(validResult2);
        assertTrue(validResult3);
    }

    private boolean patternMatching(String password) {
        if ((!PATTERN_PW_1.matcher(password).matches() && !PATTERN_PW_2.matcher(password).matches()  && !PATTERN_PW_3.matcher(password).matches() && !PATTERN_PW_4.matcher(password).matches()) || password.contains(" ")) {
            return false;
        }
        return true;
    }
}