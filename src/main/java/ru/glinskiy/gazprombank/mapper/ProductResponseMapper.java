package ru.glinskiy.gazprombank.mapper;

import org.springframework.stereotype.Component;
import ru.glinskiy.gazprombank.dao.entity.Product;
import ru.glinskiy.gazprombank.dao.model.ProductResponseDto;

@Component
public class ProductResponseMapper {

    public ProductResponseDto convert(Product source) {
        return ProductResponseDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .kcal(source.getKcal())
                .build();
    }
}
