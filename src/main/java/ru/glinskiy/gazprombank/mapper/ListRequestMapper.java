package ru.glinskiy.gazprombank.mapper;

import org.springframework.stereotype.Component;
import ru.glinskiy.gazprombank.dao.entity.List;
import ru.glinskiy.gazprombank.dao.model.ListRequestDto;

@Component
public class ListRequestMapper {

    public List convert(ListRequestDto source) {
        return List.builder()
                .name(source.getName().trim())
                .build();
    }
}
