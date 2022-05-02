package com.bosenet.iss.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String name;
	
	String teamLead;
	
	@ManyToMany
	@JoinTable(
			name = "contributors_teams",
			joinColumns = @JoinColumn(name = "team_id"),
			inverseJoinColumns = @JoinColumn(name = "contributor_id")
	)
	List<Contributor> contributors;
	
	@JsonIgnore
	@OneToMany(mappedBy = "team")
	List<Project> projects;
	
	public void assignContributor(Contributor contributor) {
		this.contributors.add(contributor);
	}
}
