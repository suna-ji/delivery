package com.example.delivery.mapper;

import com.example.delivery.dto.Delivery;
import com.example.delivery.dto.searchoption.DeliverySearchOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeliveryMapper {

    List<Delivery> selectDeliveryByDate(DeliverySearchOption searchOption);

    int update(Delivery delivery);
}
