package com.corporation.controller.api;

import com.corporation.dto.RoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/role")
@Tag(name = "Управление ролями", description = "Создание ролей")
public interface RoleApi {

    @Operation(summary = "Добавление новой роли", description = "Позволяет создать новую роль")
    @PutMapping
    RoleDto addRole(@RequestBody RoleDto roleDto);

    @Operation(summary = "Изменение роли", description = "Позволяет внести изменения в роль")
    @PostMapping
    RoleDto updateRole(@RequestBody RoleDto roleDto);

    @Operation(summary = "Удаление роли", description = "Позволяет удалить роль по ID")
    @DeleteMapping("/{id}")
    void deleteRole(@PathVariable("id") Long id);

    @Operation(summary = "Получение списка ролей", description = "Позволяет получать роли по заданной маске")
    @GetMapping
    Page<RoleDto> getRoles(
            @RequestParam(value = "Маска поиска", defaultValue = "") String keyword,
            @RequestParam(value = "Номер страницы", defaultValue = "0") int pageNumber,
            @RequestParam(value = "Элементов на странице", defaultValue = "10") int pageSize);
}