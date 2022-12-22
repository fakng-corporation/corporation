package com.corporation.controller.api;

import com.corporation.dto.RoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/role")
@Tag(name = "Управление ролями", description = "Создание ролей")
public interface RoleApi {

    @Operation(summary = "Добавление новой роли", description = "Позволяет создать новую роль")
    @PutMapping
    RoleDto addRole(@RequestBody RoleDto roleDto);

    @Operation(summary = "Удаление роли", description = "Позволяет удалить роль по ID")
    @DeleteMapping("/{id}")
    void deleteRole(@PathVariable("id") Long id);
}