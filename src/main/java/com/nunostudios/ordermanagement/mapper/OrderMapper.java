package com.nunostudios.ordermanagement.mapper;

import com.nunostudios.ordermanagement.dto.OrderRequestDTO;
import com.nunostudios.ordermanagement.dto.OrderResponseDTO;
import com.nunostudios.ordermanagement.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequestDTO dto);

    OrderResponseDTO toResponseDto(Order order);
}
