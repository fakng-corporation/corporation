package com.corporation.controller;

import com.corporation.controller.api.TeamApi;
import com.corporation.dto.TeamDto;
import com.corporation.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TeamController implements TeamApi {

    private final TeamService teamService;

    @Override
    public TeamDto addTeam(TeamDto teamDto) {
        return teamService.add(teamDto);
    }

    @Override
    public TeamDto updateTeam(TeamDto teamDto) {
        return teamService.update(teamDto);
    }

    @Override
    public void deleteTeam(Long id) {
        teamService.delete(id);
    }

    @Override
    public Page<TeamDto> getTeams(long projectId, String keyword, int pageNumber, int pageSize) {
        return teamService.getTeamsByTitle(projectId, keyword, pageNumber, pageSize);
    }
}
