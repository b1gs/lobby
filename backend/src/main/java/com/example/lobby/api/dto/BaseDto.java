package com.example.lobby.api.dto;

import lombok.Data;

@Data
public abstract class BaseDto {

    private Long id;
    private Integer version = -1;

}
