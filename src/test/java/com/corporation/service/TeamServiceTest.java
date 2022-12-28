package com.corporation.service;

import com.corporation.dto.TeamDto;
import com.corporation.exception.NotUniqueEntityException;
import com.corporation.mapper.TeamMapper;
import com.corporation.model.Project;
import com.corporation.model.Team;
import com.corporation.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
    @Mock
    private TeamRepository teamRepository;

    @Mock
    private ProjectService projectService;

    @Spy
    @InjectMocks
    private TeamMapper teamMapper = Mappers.getMapper(TeamMapper.class);

    @InjectMocks
    private TeamService teamService;

    @Test
    public void addShouldReturnCreatedTeamWithOwner() {
        long teamId = 10;
        String title = "new team";
        long projectId = 5;
        Project project = Project.builder().id(projectId).build();
        Team team = Team.builder().id(teamId).title(title).project(project).build();
        TeamDto teamDto = teamMapper.toDto(team);

        Mockito.when(projectService.findById(projectId))
                .thenReturn(project);
        Mockito.when(teamRepository.existsByProjectAndTitle(project, title)).thenReturn(false);
        Mockito.when(teamRepository.save(team))
                .thenReturn(team);
        TeamDto returnedTeamDto = teamService.add(teamDto);

        Assertions.assertEquals(teamId, returnedTeamDto.getId());
        Assertions.assertEquals(title, returnedTeamDto.getTitle());
        Assertions.assertEquals(projectId, returnedTeamDto.getProjectId());
    }

    @Test
    public void addShouldThrowNotUniqueEntityException() {
        long teamId = 10;
        String title = "new team";
        long projectId = 5;
        Project project = Project.builder().id(projectId).build();
        Team team = Team.builder().id(teamId).title(title).project(project).build();
        TeamDto teamDto = teamMapper.toDto(team);
        Mockito.when(projectService.findById(projectId))
                .thenReturn(project);
        Mockito.when(teamRepository.existsByProjectAndTitle(project, title)).thenReturn(true);

        Assertions.assertThrows(
                NotUniqueEntityException.class, () -> teamService.add(teamDto)
        );
    }

    @Test
    public void updateShouldReturnUpdatedTeamWithOwner() {
        long teamId = 10;
        String oldTitle = "old title";
        String newTitle = "new title";
        long projectId = 5;
        Project project = Project.builder().id(projectId).build();
        Team team = Team.builder().id(teamId).title(oldTitle).project(project).build();
        Team updatedTeam = Team.builder().id(teamId).title(newTitle).build();
        TeamDto teamDto = teamMapper.toDto(updatedTeam);

        Mockito.when(teamRepository.findById(teamId))
                .thenReturn(Optional.of(team));
        Mockito.when(teamRepository.existsByProjectAndTitle(project, newTitle)).thenReturn(false);
        Mockito.when(teamRepository.save(team))
                .thenReturn(updatedTeam);
        TeamDto returnedTeamDto = teamService.update(teamDto);

        Assertions.assertEquals(teamId, returnedTeamDto.getId());
        Assertions.assertEquals(newTitle, returnedTeamDto.getTitle());
    }

    @Test
    public void updateShouldThrowEntityNotFoundException() {

        long id = 100;
        TeamDto teamDto = TeamDto.builder().id(id).build();

        Mockito.when(teamRepository.findById(any(long.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EntityNotFoundException.class, () -> teamService.update(teamDto)
        );
    }

    @Test
    public void shouldDeleteTeam() {

        Team team = Team.builder().id(any(Long.class)).build();
        teamService.delete(team.getId());

        Mockito.verify(teamRepository).deleteById(team.getId());
    }

    @Test
    public void shouldReturnTeamByTitle() {
        long teamId = 10;
        String title = "title";
        long projectId = 5;
        Project project = Project.builder().id(projectId).build();
        Team mockTeam = Team.builder().id(teamId).title(title).project(project).build();
        List<Team> teamList = Collections.singletonList(mockTeam);
        Page<Team> teamPage = new PageImpl<>(teamList);
        TeamDto mockTeamDto = teamMapper.toDto(mockTeam);
        List<TeamDto> teamDtoList = Collections.singletonList(mockTeamDto);
        Pageable pageable = PageRequest.of(0, 5);

        Mockito.when(projectService.findById(projectId)).thenReturn(project);
        Mockito.when(teamRepository.findByProjectAndTitleContainingIgnoreCase(project, title, pageable))
                .thenReturn(teamPage);
        Page<TeamDto> page = teamService.getTeamsByTitle(projectId, title, 0, 5);

        Assertions.assertEquals(page.getTotalElements(), 1);
        Assertions.assertEquals(page.getContent(), teamDtoList);

    }
}
