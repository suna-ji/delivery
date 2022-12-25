package com.example.delivery.dto;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String userName;
    private String password;
    private Timestamp insertDateTime;
    private Timestamp updateDateTime;
}
