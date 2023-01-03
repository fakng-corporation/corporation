package com.corporation.service;

import com.corporation.dto.TeamDto;
import com.corporation.exception.NotUniqueEntityException;
import com.corporation.mapper.TeamMapper;
import com.corporation.model.Project;
import com.corporation.model.Team;
import com.corporation.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final ProjectService projectService;
    private final TeamMapper teamMapper;

    public TeamDto add(TeamDto teamDto) {
        Project project = projectService.findById(teamDto.getProjectId());
        if (!teamRepository.existsByProjectAndTitle(project, teamDto.getTitle())) {
            Team team = teamMapper.toEntity(teamDto);
            team.setProject(project);
            return saveEntityAndReturnDto(team);
        } else {
            throw new NotUniqueEntityException(
                    String.format("Team with title %s already exists", teamDto.getTitle())
            );
        }
    }

    public TeamDto update(TeamDto teamDto) {
        return teamRepository.findById(teamDto.getId())
                .map(team -> {
                    if (!teamRepository.existsByProjectAndTitle(team.getProject(), teamDto.getTitle())) {
                        teamMapper.updateFromDto(teamDto, team);
                        return saveEntityAndReturnDto(team);
                    } else {
                        throw new NotUniqueEntityException(
                                String.format("Team with title %s already exists", teamDto.getTitle())
                        );
                    }
                })
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Team with id %d does not exist.", teamDto.getId())));
    }

    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    public Page<TeamDto> getTeamsByTitle(long projectId, String keyword, int pageNumber, int pageSize) {
        Project project = projectService.findById(projectId);
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return teamRepository.findByProjectAndTitleContainingIgnoreCase(project, keyword, page).map(teamMapper::toDto);
    }

    private TeamDto saveEntityAndReturnDto(Team team) {
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }
}
