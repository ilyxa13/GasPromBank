package ru.glinskiy.gazprombank.dao.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class ProductRequestDto {

    private String name;

    private String description;

    private Integer kcal;
}
