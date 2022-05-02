package com.bosenet.iss.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	String status;
	String message;
}
