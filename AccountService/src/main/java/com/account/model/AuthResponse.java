package com.account.model;

public class AuthResponse {
	private final String token;

	public AuthResponse(String token) {
		this.token = token;
	}

	public String gettoken() {
		return token;
	}
}
