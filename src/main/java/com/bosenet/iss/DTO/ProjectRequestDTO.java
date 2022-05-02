package com.bosenet.iss.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Long projectManagerId;
	
	private Long teamId;
}
