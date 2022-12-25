package com.example.delivery.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
}
