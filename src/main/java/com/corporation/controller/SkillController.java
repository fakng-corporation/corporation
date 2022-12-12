package com.corporation.controller;

import com.corporation.dto.SkillDto;
import com.corporation.exception.NotUniqueSkillException;
import com.corporation.mapper.SkillMapper;
import com.corporation.model.Skill;
import com.corporation.service.SkillService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bleschunov Dmitry
 */
@Data
@Controller
@RequestMapping("/skill")
public class SkillController {

    private final SkillService skillService;

    @PostMapping("")
    public ResponseEntity<SkillDto> createSkill(@RequestBody Skill skill) throws NotUniqueSkillException {
        skill = skillService.save(skill);
        return ResponseEntity.ok(SkillMapper.INSTANCE.skillToSkillDto(skill));
    }
}
