package com.example.delivery.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DoroMapper {

    Integer selectDoroId(@Param("sidoId") Integer sidoId, @Param("sigunguId") Integer sigunguId, @Param("doroNameId") Integer doroNameId, @Param("buildingNumber") Integer buildingNumber);
}
