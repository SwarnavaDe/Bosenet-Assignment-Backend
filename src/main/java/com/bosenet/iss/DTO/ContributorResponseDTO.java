package com.bosenet.iss.DTO;

import java.util.List;

import com.bosenet.iss.model.Contributor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContributorResponseDTO {
	Contributor contributor;
	List<String> teamNames;
	List<String> projectNames;
	
}
