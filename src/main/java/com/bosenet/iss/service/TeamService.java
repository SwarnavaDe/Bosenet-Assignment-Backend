package com.bosenet.iss.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosenet.iss.DTO.ProjectRequestDTO;
import com.bosenet.iss.Exception.ProjectNotFoundException;
import com.bosenet.iss.Exception.TeamNotFoundException;
import com.bosenet.iss.model.Contributor;
import com.bosenet.iss.model.Project;
import com.bosenet.iss.model.Team;
import com.bosenet.iss.repository.ProjectRepository;
import com.bosenet.iss.repository.TeamRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TeamService {
	
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	public List<Team> getAllTeams(){
		log.info("In get all teams service");
		return teamRepository.findAll();
	}
	
	public void assignContributorToTeam(List<Contributor> contributors,Team team) throws TeamNotFoundException {
		
		log.info("In assigning contributors to a team service");
		
		Team teamToUpdate = teamRepository.findById(team.getId())
										  .orElseThrow(()-> new TeamNotFoundException("Team is not found"));
		for(Contributor contributor:contributors) {
			if(teamToUpdate.getContributors().stream().filter(x->x.getId()==contributor.getId()).collect(Collectors.toList()).size() == 0){
				teamToUpdate.assignContributor(contributor);	
			}
		}
		teamRepository.save(teamToUpdate);
	}
	
	public void assignTeamToProject(ProjectRequestDTO project) throws ProjectNotFoundException, TeamNotFoundException {
		log.info("In assigning team to a project service");
		Project projectToUpdate = projectRepository.findById(project.getId())
												   .orElseThrow(()-> new ProjectNotFoundException("Project Not found"));
		
		Team team = teamRepository.findById(project.getTeamId())
								  .orElseThrow(()-> new TeamNotFoundException("Team is not found"));
		
		projectToUpdate.setTeam(team);
		projectRepository.save(projectToUpdate);
		
	}

	public Team getTeamById(Long id) throws TeamNotFoundException {
		
		return teamRepository.findById(id)
							 .orElseThrow(()-> new TeamNotFoundException("Team is not found"));
	}
}