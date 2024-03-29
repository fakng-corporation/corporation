package com.corporation.controller.api;

import com.corporation.dto.TeamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/team")
@Tag(name = "Управление командами проекта", description = "Создание, поиск, модификация команд")
public interface TeamApi {

    @Operation(summary = "Создание новой команды", description = "Позволяет создать новую команду в проекте")
    @PostMapping
    TeamDto addTeam(@RequestBody TeamDto teamDto);

    @Operation(summary = "Изменение команды", description = "Позволяет изменить атрибуты команды в проекте")
    @PutMapping
    TeamDto updateTeam(@RequestBody TeamDto teamDto);

    @Operation(summary = "Удаление команды", description = "Позволяет удалить команду по Id")
    @DeleteMapping("/{id}")
    void deleteTeam(@PathVariable("id") long id);

    @Operation(summary = "Получение списка команд проекта", description = "Позволяет получать команды по заданной маске")
    @GetMapping
    Page<TeamDto> getTeams(
            @RequestParam(value = "ID проекта") long projectId,
            @RequestParam(value = "Маска поиска", defaultValue = "") String keyword,
            @RequestParam(value = "Номер страницы", defaultValue = "0") int pageNumber,
            @RequestParam(value = "Элементов на странице", defaultValue = "10") int pageSize);

    @Operation(summary = "Приглашение пользователя в команду",
            description = "Отправляет пользователю приглашение в команду в проекте")
    @PostMapping("/member/{id}")
    void inviteMember(@AuthenticationPrincipal(expression = "id") long senderId,
                      @PathVariable("id") long teamId,
                      @RequestParam(value = "ID пользователя") long inviteUserId);

    @Operation(
            summary = "Активация кода приглашения в команду",
            description = "Позволяет активировать код, полученный по приглашению"
    )
    @PostMapping("/invite/{code}")
    void acceptInvite(@AuthenticationPrincipal(expression = "id") long userId,
                      @Parameter(description = "Указать код для активации пользователя", required = true)
                      @PathVariable String code);

    @Operation(
            summary = "Получение команды по ID",
            description = "Позволяет получить команду по ID"
    )
    @GetMapping("/{id}")
    TeamDto getTeam(@Parameter(description = "Укажите ID команды", required = true)
                    @PathVariable long id);

    @Operation(summary = "Удаление члена команды", description = "Позволяет удалить члена команды по Id")
    @DeleteMapping("/member/{id}")
    void removeMember(@AuthenticationPrincipal(expression = "id") long ownerId,
                      @PathVariable("id") long id,
                      @RequestParam(value = "ID команды") long teamId);
}
