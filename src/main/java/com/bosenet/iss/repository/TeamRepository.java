package com.bosenet.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosenet.iss.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
