package ru.glinskiy.gazprombank.service.Impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.glinskiy.gazprombank.dao.entity.Product;
import ru.glinskiy.gazprombank.dao.model.ProductRequestDto;
import ru.glinskiy.gazprombank.dao.model.ProductResponseDto;
import ru.glinskiy.gazprombank.mapper.ProductRequestMapper;
import ru.glinskiy.gazprombank.mapper.ProductResponseMapper;
import ru.glinskiy.gazprombank.repository.ProductRepository;
import ru.glinskiy.gazprombank.service.ProductService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private static final String EXCEPTION_MSG = "Продукт с id %s не найден";

    private final ProductRepository productRepository;

    private final ProductRequestMapper productRequestMapper;

    private final ProductResponseMapper productResponseMapper;

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepository.findAll().stream()
                .map(product -> productResponseMapper.convert(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Product product = productRequestMapper.convert(productRequestDto);
        productRepository.saveAndFlush(product);
        return productResponseMapper.convert(product);
    }

    @Override
    public ProductResponseDto get(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MSG, id)));
        return productResponseMapper.convert(product);
    }

    @Override
    public ProductResponseDto update(ProductRequestDto productRequestDto, long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MSG, id)));

        if(StringUtils.isNotBlank(productRequestDto.getName())) {
            product.setName(productRequestDto.getName().trim());
        }
        if(StringUtils.isNotBlank(productRequestDto.getDescription())) {
            product.setDescription(productRequestDto.getDescription().trim());
        }
        if(productRequestDto.getKcal() != null) {
            product.setKcal(productRequestDto.getKcal());
        }
        productRepository.saveAndFlush(product);
        return productResponseMapper.convert(product);
    }

    @Override
    public void delete(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MSG, id)));
        productRepository.delete(product);
    }


}
