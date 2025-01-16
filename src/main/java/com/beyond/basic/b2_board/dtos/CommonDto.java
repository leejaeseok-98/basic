package com.beyond.basic.b2_board.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonDto {
    private int status_code;
    private String status_message;
    private Object result;
}
