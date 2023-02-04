package com.corporation.controller;

import com.corporation.dto.TeamDto;
import com.corporation.model.Team;
import com.corporation.service.TeamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    public void shouldReturnCreatedTeamDto() {

        long id = 777;
        String title = "new team";

        TeamDto teamDto = TeamDto.builder().title(title).build();
        TeamDto addedTeamDto = TeamDto.builder().id(id).title(title).build();

        Mockito.when(teamService.add(teamDto)).thenReturn(addedTeamDto);

        TeamDto createdTeamDto = teamController.addTeam(teamDto);

        Assertions.assertEquals(id, createdTeamDto.getId());
        Assertions.assertEquals(title, createdTeamDto.getTitle());
    }

    @Test
    public void shouldReturnUpdatedTeamDto() {

        long id = 777;
        String newTitle = "new team";
        TeamDto addedTeamDto = TeamDto.builder().id(id).title(newTitle).build();

        Mockito.when(teamService.update(addedTeamDto)).thenReturn(addedTeamDto);

        TeamDto resultTeamDto = teamController.updateTeam(addedTeamDto);

        Assertions.assertEquals(id, resultTeamDto.getId());
        Assertions.assertEquals(newTitle, resultTeamDto.getTitle());
    }

    @Test
    public void shouldDeleteTeam() {
        Team team = Team.builder().id(any(Long.class)).build();
        teamController.deleteTeam(team.getId());

        Mockito.verify(teamService).delete(team.getId());
    }

    @Test
    public void shouldReturnTeamByTitle() {
        long teamId = 10;
        String title = "title";
        long projectId = 5;
        TeamDto mockTeamDto = TeamDto.builder().id(teamId).title(title).build();
        List<TeamDto> teamDtoList = Collections.singletonList(mockTeamDto);
        Page<TeamDto> teamDtoPage = new PageImpl<>(teamDtoList);

        Mockito.when(teamService.getTeamsByTitle(projectId, title, 0, 5))
                .thenReturn(teamDtoPage);
        Page<TeamDto> page = teamController.getTeams(projectId, title, 0, 5);

        Assertions.assertEquals(page.getTotalElements(), teamDtoPage.getTotalElements());
        Assertions.assertEquals(page.getContent(), teamDtoPage.getContent());
    }

    @Test
    public void shouldDeleteMemberFromTeam() {
        long teamId = 111;
        long userId = 777;
        long ownerId = 555;

        teamController.removeMember(ownerId, userId, teamId);

        Mockito.verify(teamService).removeMember(ownerId, userId, teamId);
    }
}
