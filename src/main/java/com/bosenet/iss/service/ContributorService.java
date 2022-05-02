package com.bosenet.iss.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosenet.iss.DTO.ContributorResponseDTO;
import com.bosenet.iss.model.Contributor;
import com.bosenet.iss.model.Team;
import com.bosenet.iss.repository.ContributorRepository;
import com.bosenet.iss.repository.TeamRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContributorService {
	
	@Autowired
	ContributorRepository contributorRepository;
	
	@Autowired
	TeamRepository teamRepository;
	
	public Contributor addContributor(Contributor contributor) {
		log.info("In add new contributor service");
		return contributorRepository.save(contributor);	
	}
	public List<Contributor> getAllContributors(){
		log.info("In getting all contributor service");
		return contributorRepository.findAll();
	}
	public List<ContributorResponseDTO> viewContributors(String startOnboardDateRequest, String endOnboardDateRequest) throws ParseException{
		log.info("In view all contributors by date range service");
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			    	      
		List<Contributor> contributors = new ArrayList<>();
		//getting all contributors if no date range is provided
		if(startOnboardDateRequest.length()==0 && endOnboardDateRequest.length()== 0) {
			contributors = contributorRepository.findAll();
		}
		else {
			Date startOnboardDate = sdformat.parse(startOnboardDateRequest);
		    Date endOnboardDate = sdformat.parse(endOnboardDateRequest);
			//getting all contributors that belong to a particular on-board date range
			contributors = contributorRepository.findAll().stream()
										.filter(contributor->contributor.getOnboardDate()
																		.compareTo(startOnboardDate)>=0 && contributor.getOnboardDate().compareTo(endOnboardDate)<=0)
										.collect(Collectors.toList());
		}
		
		List<ContributorResponseDTO> contributorResponses = new ArrayList<>();
		
		for(Contributor contributor:contributors) {
			//creating the custom contributor object
			ContributorResponseDTO newContributorResponseDTO = new ContributorResponseDTO();
			newContributorResponseDTO.setContributor(contributor);
			//getting team names of a contributor
			newContributorResponseDTO.setTeamNames(contributor.getTeams().stream().map(team->team.getName()).collect(Collectors.toList()));
			//getting project names associated to a contributor
			newContributorResponseDTO.setProjectNames(getProjectNamesOfContributor(contributor));
			contributorResponses.add(newContributorResponseDTO);
		}
		return contributorResponses; 
	}
	
	private List<String> getProjectNamesOfContributor(Contributor contributor){
		log.info("In get project names of contributor helper method");
		List<String> projectsOfContributor = new ArrayList<>();
		for(Team team:contributor.getTeams()) {
			team.getProjects().stream().map(project->projectsOfContributor.add(project.getName())).collect(Collectors.toList());
		}
		return projectsOfContributor;
		 
	} 
}
