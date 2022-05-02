package com.bosenet.iss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosenet.iss.DTO.ResponseDTO;
import com.bosenet.iss.Exception.TeamNotFoundException;
import com.bosenet.iss.model.Team;
import com.bosenet.iss.service.TeamService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/iss/team")
@Slf4j
public class TeamController {
	
	@Autowired
	TeamService teamService;
	
	@GetMapping("/get-all")
	public ResponseEntity<List<Team>> getAllTeam(){
		log.info("In get all teams controller");
		return new ResponseEntity<>(teamService.getAllTeams(),HttpStatus.OK);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getTeamById(@PathVariable Long id){		
		try {
			log.info("In get team by id controller");
			return new ResponseEntity<>(teamService.getTeamById(id),HttpStatus.OK);
		} catch (TeamNotFoundException e) {
			log.error("Error occurred in get team by id controller - "+e.getMessage());
			return new ResponseEntity<>(new ResponseDTO("Failed",e.getMessage()),HttpStatus.NOT_FOUND);
		}
	}
}
