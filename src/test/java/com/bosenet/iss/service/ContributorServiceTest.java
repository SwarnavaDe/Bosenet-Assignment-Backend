/**
 * 
 */
package com.bosenet.iss.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bosenet.iss.DTO.ContributorResponseDTO;
import com.bosenet.iss.model.Contributor;
import com.bosenet.iss.model.Team;
import com.bosenet.iss.repository.ContributorRepository;
import com.bosenet.iss.repository.TeamRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ContributorServiceTest {
	@InjectMocks
	ContributorService contributorService;
	
	@Mock
	ContributorRepository contributorRepository;
	
	@Mock
	TeamRepository teamRepository;
	

	@Test
	void addContributorTest() {
		Contributor contributor = new Contributor();

		contributor.setId(1L);
		contributor.setName("Shome Das");
		contributor.setNationality("Indian");
		contributor.setOnboardDate(new Date());
		contributor.setExitDate(new Date());
		
		Mockito.when(contributorRepository.save(contributor)).thenReturn(contributor);
		assertEquals(1L, contributorService.addContributor(contributor).getId());
		
	}
	
	@Test
	void getAllContributorsTest() {
		List<Contributor> contributors = new ArrayList<>();
		
		Contributor contributor = new Contributor();
		
		contributor.setId(1L);
		contributor.setName("Shome Das"); 
		contributor.setNationality("Indian");
		contributor.setOnboardDate(new Date());
		contributor.setExitDate(new Date());
		
		Contributor contributor2 = new Contributor();
		
		contributor2.setId(2L);
		contributor2.setName("Das");
		contributor2.setNationality("Indian");
		contributor2.setOnboardDate(new Date());
		contributor2.setExitDate(new Date());
		
		contributors.add(contributor);
		contributors.add(contributor2);
		
		Mockito.when(contributorRepository.findAll()).thenReturn(contributors);
		
		assertEquals(1L, contributorService.getAllContributors().get(0).getId());
	}
	
	@Test
	void viewContributorsTest() throws ParseException {
		String startOnboardDateRequest="2020-05-01T09:18:18Z";
		String endOnboardDateRequest="2020-09-01T09:18:18Z";
		
		List<Contributor> contributors = new ArrayList<>();
		
		Contributor contributor = new Contributor();
		
		contributor.setId(1L);
		contributor.setName("Shome Das");
		contributor.setNationality("Indian");
		contributor.setOnboardDate(new Date());
		contributor.setExitDate(new Date());
		
		Contributor contributor2 = new Contributor();
		
		contributor2.setId(2L);
		contributor2.setName("Das");
		contributor2.setNationality("Indian");
		contributor2.setOnboardDate(new Date());
		contributor2.setExitDate(new Date());
		
		contributors.add(contributor);
		contributors.add(contributor2);
		
		Mockito.when(contributorRepository.findAll()).thenReturn(contributors);
		
		List<ContributorResponseDTO> contributorResponses = new ArrayList<>();
		ContributorResponseDTO newContributorResponseDTO = new ContributorResponseDTO();
		newContributorResponseDTO.setContributor(contributor);
		
		contributorResponses.add(newContributorResponseDTO);
		
		assertEquals(0, contributorService.viewContributors(startOnboardDateRequest, endOnboardDateRequest).size());
		
				
	}
	
}
