package com.bosenet.iss.Exception;

public class ProjectNotFoundException extends Exception {
	/**
	 * Default Serializable id
	 */
	private static final long serialVersionUID = 1L;

	public ProjectNotFoundException(String message) {
		super(message);
	}
}
