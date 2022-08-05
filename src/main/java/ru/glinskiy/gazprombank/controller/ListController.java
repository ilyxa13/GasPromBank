package ru.glinskiy.gazprombank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.glinskiy.gazprombank.dao.model.ListRequestDto;
import ru.glinskiy.gazprombank.dao.model.ListResponseDto;
import ru.glinskiy.gazprombank.service.ListService;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
@RequiredArgsConstructor
@Tag(name = "lists", description = "API списков покупок")
public class ListController {

    private final ListService listService;

    @Operation(summary = "Получить все списки продуктов", tags = {"lists"})
    @GetMapping
    public List<ListResponseDto> getAll() {
        return listService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новый список продуктов", tags = {"lists"})
    @PostMapping
    public ListResponseDto create(@RequestBody ListRequestDto list) {
        return listService.create(list);
    }

    @Operation(summary = "Поиск списка продуктов по ID", tags = {"lists"})
    @GetMapping("/{id}")
    public ListResponseDto get(
            @Parameter(description = "ID списка продуктов",
                    example = "1",
                    required = true)
            @PathVariable("id") long id) {
        return listService.get(id);
    }

    @Operation(summary = "Обновить список продуктов по ID", tags = {"lists"})
    @PatchMapping("/{id}")
    public ListResponseDto update(
            @Parameter(description = "ID списка продуктов",
                    example = "1",
                    required = true)
            @PathVariable long id,
            @RequestBody ListRequestDto list) {
        return listService.update(list, id);
    }

    @Operation(summary = "Удалить список продуктов по ID", tags = {"lists"})
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID списка продуктов",
                    example = "1",
                    required = true)
            @PathVariable("id") long id) {
        listService.delete(id);
    }

    @Operation(summary = "Добавить продукт в список продуктов по ID", tags = {"lists"})
    @PatchMapping("/{listId}/addProduct/{productId}")
    public ListResponseDto addProduct(
            @Parameter(description = "ID списка продуктов",
                    example = "1",
                    required = true)
            @PathVariable("listId") long listId,
            @Parameter(description = "ID продукта",
                    example = "1",
                    required = true)
            @PathVariable("productId") long productId) {
        return listService.addProduct(listId, productId);
    }
}
