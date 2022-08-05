package ru.glinskiy.gazprombank.service;

import ru.glinskiy.gazprombank.dao.model.ProductRequestDto;
import ru.glinskiy.gazprombank.dao.model.ProductResponseDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> getAll();

    ProductResponseDto create(ProductRequestDto productRequestDto);

    ProductResponseDto get(long id);

    ProductResponseDto update(ProductRequestDto productRequestDto, long id);

    void delete(long id);
}
