package com.bosenet.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosenet.iss.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	
}
