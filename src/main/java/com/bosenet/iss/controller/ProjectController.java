package com.bosenet.iss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosenet.iss.model.Project;
import com.bosenet.iss.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("iss/project")
@Slf4j
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@GetMapping("/get-all")
	public ResponseEntity<List<Project>> getAllProjects(){
		log.info("In get all projects controller");
		return new ResponseEntity<>(projectService.getAllProjects(),HttpStatus.OK);
	}
}
