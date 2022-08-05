package ru.glinskiy.gazprombank.service.Impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.glinskiy.gazprombank.dao.entity.List;
import ru.glinskiy.gazprombank.dao.entity.Product;
import ru.glinskiy.gazprombank.dao.model.ListRequestDto;
import ru.glinskiy.gazprombank.dao.model.ListResponseDto;
import ru.glinskiy.gazprombank.dao.model.ProductResponseDto;
import ru.glinskiy.gazprombank.mapper.ListRequestMapper;
import ru.glinskiy.gazprombank.mapper.ListResponseMapper;
import ru.glinskiy.gazprombank.repository.ListRepository;
import ru.glinskiy.gazprombank.repository.ProductRepository;
import ru.glinskiy.gazprombank.service.ListService;
import ru.glinskiy.gazprombank.service.ProductService;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {

    private static final String EXCEPTION_MSG = "Список продуктов с id %s не найден";

    private final ListRepository listRepository;

    private final ListResponseMapper listResponseMapper;

    private final ListRequestMapper listRequestMapper;
    
    private final ProductRepository productRepository;

    @Override
    public java.util.List<ListResponseDto> getAll() {
        return listRepository.findAll().stream()
                .map(list -> {
                    ListResponseDto listResponseDto = listResponseMapper.convert(list);
                    listResponseDto.setProductsName(getProductsNameFromList(list));
                    listResponseDto.setSumKcal(getSumKcalFromList(list));
                    return listResponseDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ListResponseDto create(ListRequestDto listRequestDto) {
        List list = listRequestMapper.convert(listRequestDto);
        listRepository.saveAndFlush(list);
        return listResponseMapper.convert(list);
    }

    @Override
    public ListResponseDto get(long id) {
        List list = listRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MSG, id)));
        ListResponseDto listResponseDto = listResponseMapper.convert(list);
        listResponseDto.setProductsName(getProductsNameFromList(list));
        listResponseDto.setSumKcal(getSumKcalFromList(list));
        return listResponseDto;
    }

    @Override
    public ListResponseDto update(ListRequestDto listRequestDto, long id) {
        List list = listRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MSG, id)));

        if(StringUtils.isNotBlank(listRequestDto.getName())) {
            list.setName(listRequestDto.getName().trim());
        }
        listRepository.saveAndFlush(list);
        return listResponseMapper.convert(list);
    }

    @Override
    public void delete(long id) {
        List list = listRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MSG, id)));
        listRepository.delete(list);
    }

    @Override
    public ListResponseDto addProduct(long listId, long productId) {
        List list = listRepository.findById(listId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MSG, listId)));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Продукт с id %s не найден", productId)));
        list.getProducts().add(product);
        listRepository.saveAndFlush(list);
        return get(listId);
    }

    private Set<String> getProductsNameFromList(List list) {
        return list.getProducts().stream().map(Product::getName).collect(Collectors.toSet());
    }

    private int getSumKcalFromList(List list) {
        return list.getProducts().stream().mapToInt(Product::getKcal).sum();
    }

}
