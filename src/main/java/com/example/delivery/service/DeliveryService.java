package com.example.delivery.service;

import com.example.delivery.dto.Delivery;
import com.example.delivery.dto.Doro;
import com.example.delivery.dto.searchoption.DeliverySearchOption;
import com.example.delivery.mapper.DeliveryMapper;
import com.example.delivery.mapper.DoroMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService {

    private final DeliveryMapper deliveryMapper;
    private final DoroMapper doroMapper;

    public List<Delivery> findByDate(DeliverySearchOption searchOption) {
        return deliveryMapper.selectDeliveryByDate(searchOption);
    }

    public int modify(Delivery delivery, Doro doro) {
        Integer doroId = doroMapper.selectDoroId(doro.getSidoId(), doro.getSigunguId(), doro.getDoroNameId(), doro.getBuildingNumber());
        if (doroId != null) {
            delivery.setDoroId(doroId);
        }
        return deliveryMapper.update(delivery);
    }


    public Delivery findById(Integer deliveryId) {
        return deliveryMapper.selectById(deliveryId);
    }
}