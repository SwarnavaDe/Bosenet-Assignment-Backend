package com.bosenet.iss.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bosenet.iss.DTO.ProjectRequestDTO;
import com.bosenet.iss.Exception.ProjectNotFoundException;
import com.bosenet.iss.Exception.TeamNotFoundException;
import com.bosenet.iss.model.Contributor;
import com.bosenet.iss.model.Project;
import com.bosenet.iss.model.Team;
import com.bosenet.iss.repository.ProjectRepository;
import com.bosenet.iss.repository.TeamRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TeamServiceTest {
	
	@InjectMocks
	TeamService teamService;
	
	@Mock
	TeamRepository teamRepository;
	
	@Mock
	ProjectRepository projectRepository;
	
	@Mock
	Team mockTeam;
	
	@Mock
	Project mockProject;
	
	
	@Test
	void getAllTeamsTest() {
		Team team = new Team();
		team.setId(1L);
		team.setName("Team A");
		team.setTeamLead("Ram");
		
		List<Team> teams = new ArrayList<>();
		
		teams.add(team);
		
		Mockito.when(teamRepository.findAll()).thenReturn(teams);
		
		assertEquals("Team A", teamService.getAllTeams().get(0).getName());
	}
	
	@Test
	void getTeamByIdExceptionTest() {
		Optional<Team> opt = Optional.empty();
		Mockito.when(teamRepository.findById(1L)).thenReturn(opt);
		TeamNotFoundException thrown = assertThrows(
		           TeamNotFoundException.class,
		           () -> teamService.getTeamById(1L),
		           "Team Not Found"
		    );

		    assertEquals("Team is not found", thrown.getMessage());
	}
	
	@Test
	void getTeamByIdTest() throws TeamNotFoundException {
		Team team = new Team();
		
		team.setId(1L);
		team.setName("Team A");
		team.setTeamLead("Ram");
		
		Optional<Team> opt = Optional.of(team);
		Mockito.when(teamRepository.findById(1L)).thenReturn(opt);

		assertEquals("Ram",teamService.getTeamById(1L).getTeamLead());
	}
	
	@Test
	void assignContributorToTeamTest() throws TeamNotFoundException {
		
		List<Contributor> contributors = new ArrayList<>();
		
		Contributor contributor = new Contributor();
		
		contributor.setId(1L);
		contributor.setName("Shome Das");
		contributor.setNationality("Indian");
		contributor.setOnboardDate(new Date());
		contributor.setExitDate(new Date());
		
		Contributor contributor2 = new Contributor();
		
		contributor2.setId(2L);
		contributor2.setName("Das");
		contributor2.setNationality("Indian");
		contributor2.setOnboardDate(new Date());
		contributor2.setExitDate(new Date());
		
		contributors.add(contributor);
		contributors.add(contributor2);
		
		Team team = new Team();
		
		team.setId(1L);
		team.setName("Team A");
		team.setTeamLead("Ram");
		team.setContributors(contributors);
		
		Mockito.when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
		
		Mockito.when(teamRepository.save(team)).thenReturn(team);
		
		teamService.assignContributorToTeam(contributors, team);
		
		Mockito.verify(mockTeam, times(0)).assignContributor(contributor2);
	}
	
	@Test
	void assignTeamToProjectTest() throws ProjectNotFoundException, TeamNotFoundException {
		Project project = new Project();
		project.setId(1L);
		project.setName("Rover");
		project.setProjectManagerId(1222L);
		
		Team team = new Team();
		
		team.setId(1L);
		team.setName("Team A");
		team.setTeamLead("Ram");
		//Project mockProject = Mockito.mock(Project.class);
		ProjectRequestDTO request = new ProjectRequestDTO();
		
		request.setId(1L);
		request.setName("Rover");
		request.setProjectManagerId(1222L);
		request.setTeamId(1l);
		Optional<Team> optTeam = Optional.of(team);
		Optional<Project> optProject = Optional.of(project);
		Mockito.when(projectRepository.findById(request.getId())).thenReturn(optProject);
		Mockito.when(teamRepository.findById(request.getTeamId())).thenReturn(optTeam);
		
		teamService.assignTeamToProject(request);
		
	
		Mockito.verify(mockProject, times(0)).setTeam(team);
		
		
	}
}
