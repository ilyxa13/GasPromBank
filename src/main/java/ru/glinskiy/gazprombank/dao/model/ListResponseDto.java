package ru.glinskiy.gazprombank.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ListResponseDto {

    private long id;

    private String name;

    private Set<String> productsName;

    private int sumKcal;
}
