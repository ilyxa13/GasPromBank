package ru.glinskiy.gazprombank.mapper;

import org.springframework.stereotype.Component;
import ru.glinskiy.gazprombank.dao.entity.Product;
import ru.glinskiy.gazprombank.dao.model.ProductRequestDto;

@Component
public class ProductRequestMapper {

    public Product convert(ProductRequestDto source) {
        return Product.builder()
                .name(source.getName().trim())
                .description(source.getDescription().trim())
                .kcal(source.getKcal())
                .build();
    }
}
