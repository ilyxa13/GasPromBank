package ru.glinskiy.gazprombank.mapper;

import org.springframework.stereotype.Component;
import ru.glinskiy.gazprombank.dao.entity.List;
import ru.glinskiy.gazprombank.dao.entity.Product;
import ru.glinskiy.gazprombank.dao.model.ListResponseDto;

import java.util.stream.Collectors;

@Component
public class ListResponseMapper {

    public ListResponseDto convert(List source) {
        return ListResponseDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
