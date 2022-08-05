package ru.glinskiy.gazprombank.service;

import ru.glinskiy.gazprombank.dao.model.ListRequestDto;
import ru.glinskiy.gazprombank.dao.model.ListResponseDto;

import java.util.List;

public interface ListService {

    List<ListResponseDto> getAll();

    ListResponseDto create(ListRequestDto listRequestDto);

    ListResponseDto get(long id);

    ListResponseDto update(ListRequestDto listRequestDto, long id);

    void delete(long id);

    ListResponseDto addProduct(long listId, long productId);
}
