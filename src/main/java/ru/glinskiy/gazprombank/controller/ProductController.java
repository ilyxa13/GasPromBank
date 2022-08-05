package ru.glinskiy.gazprombank.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.glinskiy.gazprombank.dao.model.ProductRequestDto;
import ru.glinskiy.gazprombank.dao.model.ProductResponseDto;
import ru.glinskiy.gazprombank.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "products", description = "API продуктов")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Получить все продукты", tags = {"products"})
    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать новый продукт", tags = {"products"})
    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto product) {
        return productService.create(product);
    }

    @Operation(summary = "Поиск продукта по ID", tags = {"products"})
    @GetMapping("/{id}")
    public ProductResponseDto get(
            @Parameter(description = "ID продукта",
                    example = "1",
                    required = true)
            @PathVariable("id") long id) {
        return productService.get(id);
    }

    @Operation(summary = "Обновить продукт по ID", tags = {"products"})
    @PatchMapping("/{id}")
    public ProductResponseDto update(
            @Parameter(description = "ID продукта",
                    example = "1",
                    required = true)
            @PathVariable long id,
            @RequestBody ProductRequestDto product) {
        return productService.update(product, id);
    }

    @Operation(summary = "Удалить продукт по ID", tags = {"products"})
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID продукта",
                    example = "1",
                    required = true)
            @PathVariable("id") long id) {
        productService.delete(id);
    }
}
