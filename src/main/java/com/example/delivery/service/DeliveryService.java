package com.example.delivery.service;

import com.example.delivery.dto.Delivery;
import com.example.delivery.dto.searchoption.DeliverySearchOption;
import com.example.delivery.mapper.DeliveryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService {

    private final DeliveryMapper deliveryMapper;

    public List<Delivery> findByDate(DeliverySearchOption searchOption) {
        return deliveryMapper.selectDeliveryByDate(searchOption);
    }


}
