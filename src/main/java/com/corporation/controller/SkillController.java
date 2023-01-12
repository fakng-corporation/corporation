package com.corporation.controller;

import com.corporation.dto.SkillDto;
import com.corporation.mapper.SkillMapper;
import com.corporation.model.Skill;
import com.corporation.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/skill")
public class SkillController {

    private final SkillService skillService;
    private final SkillMapper skillMapper;

    @PostMapping
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
        Skill skill = skillMapper.toEntity(skillDto);
        skill = skillService.save(skill);
        return ResponseEntity.ok(skillMapper.toDto(skill));
    }

    @GetMapping
    public Page<SkillDto> getSkillsByUserId(
            @RequestParam("user_id") long userId,
            @RequestParam("page") int page,
            @RequestParam("page_size") int pageSize) {
        return skillService.findSkillsByUserId(userId, page, pageSize).map(skillMapper::toDto);
    }
}
