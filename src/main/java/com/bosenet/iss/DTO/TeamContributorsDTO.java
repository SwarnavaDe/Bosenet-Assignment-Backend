package com.bosenet.iss.DTO;

import java.io.Serializable;
import java.util.List;

import com.bosenet.iss.model.Contributor;
import com.bosenet.iss.model.Team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamContributorsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Team team;
	List<Contributor> contributors;
}
