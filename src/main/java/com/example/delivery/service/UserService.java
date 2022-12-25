package com.example.delivery.service;

import com.example.delivery.dto.TokenInfo;
import com.example.delivery.dto.User;
import com.example.delivery.mapper.UserMapper;
import com.example.delivery.token.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenManager jwtTokenManager;

    public User findById(String userId) {
        return userMapper.selectByUserId(userId);
    }

    public int add(User user) {
        if (user == null) {
            return 0;
        }
        return userMapper.insert(user);
    }

    public TokenInfo login(String userId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenManager.generateToken(authentication);

        return tokenInfo;
    }
}
