package com.bosenet.iss.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosenet.iss.DTO.ContributorResponseDTO;
import com.bosenet.iss.DTO.ProjectRequestDTO;
import com.bosenet.iss.DTO.ResponseDTO;
import com.bosenet.iss.DTO.TeamContributorsDTO;
import com.bosenet.iss.Exception.ProjectNotFoundException;
import com.bosenet.iss.Exception.TeamNotFoundException;
import com.bosenet.iss.model.Contributor;
import com.bosenet.iss.service.ContributorService;
import com.bosenet.iss.service.TeamService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/iss/contributors")
@Slf4j
public class ContributorController {
	
	@Autowired
	ContributorService contributorService;
	
	@Autowired
	TeamService teamService;
	
	@PostMapping("/add")
	public ResponseEntity<Contributor> addContributor(@RequestBody Contributor contributor){
		log.info("Inside add contributor controller method");
		Contributor newContributor = contributorService.addContributor(contributor);
		return new ResponseEntity<>(newContributor,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/get-all-contributors")
	public ResponseEntity<List<Contributor>> getAllContributors(){
		log.info("In get all contributors controller");
		return new ResponseEntity<>(contributorService.getAllContributors(),HttpStatus.OK);
	}
	
	@PutMapping("/teams/add")
	public ResponseEntity<ResponseDTO> assignContributorToTeam(@RequestBody TeamContributorsDTO teamContributorsDTO){
		try {
			log.info("Inside assigning contributor to team controller method");
			teamService.assignContributorToTeam(teamContributorsDTO.getContributors(), teamContributorsDTO.getTeam());
			
			return new ResponseEntity<>(new ResponseDTO("Success","Assigned Successfully."),HttpStatus.OK);
		}
		catch (TeamNotFoundException exception) {
			log.error("Exception occurred in assigning contributor to team controller method");
			return new ResponseEntity<>(new ResponseDTO("Failed",exception.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/project/add")
	public ResponseEntity<ResponseDTO> assignTeamToProject(@RequestBody ProjectRequestDTO project){
		try {
			log.info("Inside assigning team to project controller method");
			teamService.assignTeamToProject(project);
			return new ResponseEntity<>(new ResponseDTO("Success","Assigned Successfully."),HttpStatus.OK);
		}
		catch (ProjectNotFoundException exception) {
			log.error("Exception occurred in assigning team to project controller method - "+exception.getMessage());
			return new ResponseEntity<>(new ResponseDTO("Failed",exception.getMessage()),HttpStatus.BAD_REQUEST);
		}
		catch (TeamNotFoundException exception) {
			log.error("Exception occurred in assigning team to project controller method - "+exception.getMessage());
			return new ResponseEntity<>(new ResponseDTO("Failed",exception.getMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/view")
	public ResponseEntity<List<ContributorResponseDTO>> viewContributors(@RequestParam("start") String startOnboardDate, @RequestParam("end") String endOnboardDate){
		List<ContributorResponseDTO> contributorResponseList = new ArrayList<>();
		try {
			log.info("Inside viewing contributers by date range controller");
			contributorResponseList = contributorService.viewContributors(startOnboardDate, endOnboardDate);
			return new ResponseEntity<>(contributorResponseList,HttpStatus.OK);
		} catch (ParseException e) {
			log.error("Exception inside viewing contributers by date range controller - "+e.getMessage());
			return new ResponseEntity<>(contributorResponseList,HttpStatus.BAD_REQUEST);
		}
		
	}

}
