package com.bosenet.iss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosenet.iss.model.Project;
import com.bosenet.iss.repository.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	public List<Project> getAllProjects(){
		log.info("In get all project service");
		return projectRepository.findAll();
	}
}
 