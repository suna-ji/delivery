package com.example.delivery.dto.searchoption;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
public class DeliverySearchOption {
    private Timestamp fromDateTime;
    private Timestamp toDateTime;
    private String userId;
}
