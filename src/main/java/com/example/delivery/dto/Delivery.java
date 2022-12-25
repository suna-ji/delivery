package com.example.delivery.dto;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    private Integer deliveryId;
    private String userId;
    private Integer doroId;
    private Timestamp insertDateTime;
    private Timestamp updateDateTime;
    private String status;
}
