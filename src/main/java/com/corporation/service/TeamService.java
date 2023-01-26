package com.corporation.service;

import com.corporation.dto.TeamDto;
import com.corporation.exception.NotEnoughPermissionException;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.exception.NotUniqueEntityException;
import com.corporation.mapper.TeamMapper;
import com.corporation.model.Project;
import com.corporation.model.Team;
import com.corporation.model.User;
import com.corporation.model.service.InviteToTeam;
import com.corporation.repository.TeamRepository;
import com.corporation.repository.service.InviteToTeamRepository;
import com.corporation.service.event.MessageEventPublisher;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final TeamMapper teamMapper;
    private final MessageEventPublisher messageEventPublisher;
    private final InviteToTeamRepository inviteToTeamRepository;

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

    @Transactional
    public void inviteUserToTeam(long senderId, long teamId, long userId) {
        Team team = findById(teamId);
        User owner = team.getProject().getOwner();
        // Сейчас сравнивается владелец команды проекта и сендер
        // Доработать после добавления прав!!!!
        if (owner.getId() == senderId) {
            User recipient = userService.findById(userId);

            String code = UUID.randomUUID().toString();
            InviteToTeam inviteToTeam = InviteToTeam.builder()
                    .senderId(senderId)
                    .recipientId(userId)
                    .teamId(teamId)
                    .code(code)
                    .build();
            inviteToTeamRepository.save(inviteToTeam);

            String subject = "Invite to Team";
            String body = String.format(
                    "You were invited to team %s %s. Use code: %s",
                    team.getTitle(),
                    team.getDescription(),
                    code);

            messageEventPublisher.sendMessage(owner, recipient, body, subject);
        } else {
            throw new NotEnoughPermissionException(
                    String.format("You don't have " +
                            "permission to invite users in team with ID %d.", teamId));
        }
    }

    private Team findById(long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        return optionalTeam
                .orElseThrow(() -> new NotFoundEntityException(
                        String.format("Team with id %d does not exist.", id)
                ));
    }

    private TeamDto saveEntityAndReturnDto(Team team) {
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    @Transactional
    public void acceptInvite(long userId, String code) {
        Optional<InviteToTeam> invite = inviteToTeamRepository.findByCode(code);
        invite.ifPresentOrElse(inviteToTeam -> {
            if (inviteToTeam.getRecipientId() == userId) {
                inviteToTeamRepository.addToTeam(userId, inviteToTeam.getTeamId());
                inviteToTeamRepository.delete(invite.get());
            } else {
                throw new NotFoundEntityException(
                        "Invite does not exist.");
            }
        }, () -> {
            throw new NotFoundEntityException(
                    "Invite does not exist.");
        });
    }

    public TeamDto getTeamById(long id) {
        return teamMapper.toDto(findById(id));
    }
}
