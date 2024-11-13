package com.introcopilot.user.token;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.introcopilot.user.model.User;

@Document("token")
public class Token {
	@Id
	private String id;
	private String token;

	@Field("token_type")  // Optionally, customize the field name
    private TokenType tokenType;

	@DBRef
	private User user;

	private boolean revoked;

	private boolean expired;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public Token() {
		super();
	}

	public Token( String token, TokenType tokenType, User user, boolean revoked, boolean expired) {
		super();
//		this.id = id;
		this.token = token;
		this.tokenType = tokenType;
		this.user = user;
		this.revoked = revoked;
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", token=" + token + ", tokenType=" + tokenType + ", user=" + user + ", revoked="
				+ revoked + ", expired=" + expired + "]";
	}
	
	
}
