package com.bosenet.iss.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bosenet.iss.model.Project;
import com.bosenet.iss.repository.ProjectRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
	
	@InjectMocks
	ProjectService projectService;
	
	@Mock
	ProjectRepository projectRepository;

	@Test
	void getAllProjectsTest() {
		Project project = new Project();
		project.setId(1L);
		project.setName("Rover");
		project.setProjectManagerId(1222L);
		
		List<Project> projects = new ArrayList<>();
		
		projects.add(project);
		
		Mockito.when(projectRepository.findAll()).thenReturn(projects);
		
		assertEquals(1L, projectService.getAllProjects().get(0).getId());
	}
	
	

}
