package com.rz.sb.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/*", filterName = "LoginFilter")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		String uri = request.getRequestURI();
		String pattern = ".*\\.(html|ico|js|css|png|jpg|woff|woff2|ttf)$";
		boolean isMatch = Pattern.matches(pattern, uri);
		boolean noFilter = isMatch || uri.equals("/user/login") || uri.equals("/");
		if (noFilter) {
			chain.doFilter(req, res);
			return;
		}
		final String authHeader = request.getHeader("authorization");
		if (authHeader == null) {
			response.sendError(450, "Missing or invalid Authorization header");
			return;
		}
		final String token = authHeader;
		try {
			final Claims claims = Jwts.parser().setSigningKey("secretkey")
					.parseClaimsJws(token).getBody();
			request.setAttribute("claims", claims);
		} catch (final SignatureException e) {
			response.sendError(403, "Invalid token");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
