package com.rz.sb.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

public class TokenUtil {

	static String secretkey = "rzy";
	static int expiration = 7200;

	public static String create(String username) {
		String token = Jwts.builder().setSubject(username)
				.claim("roles", "roles").setIssuedAt(now())
				.setExpiration(getExpiration())
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
		return token;
	}

	public static boolean valid(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretkey)
				.parseClaimsJws(token).getBody();
		Date expiration = claims.getExpiration();
		if (expiration.before(new Date())) {
			return false;
		}
		claims.setExpiration(getExpiration());
		return true;
	}

	private static Date now() {
		return new Date();
	}

	private static Date getExpiration() {
		Date now = new Date();
		Calendar ca = Calendar.getInstance();
		ca.setTime(now);
		ca.add(Calendar.SECOND, expiration);
		Date expiration = ca.getTime();
		return expiration;
	}
}
