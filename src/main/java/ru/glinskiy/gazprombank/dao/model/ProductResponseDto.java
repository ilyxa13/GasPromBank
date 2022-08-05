package ru.glinskiy.gazprombank.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponseDto {

    private long id;

    private String name;

    private String description;

    private int kcal;
}
