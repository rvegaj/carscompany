package com.carscompany.filter;

import com.carscompany.common.Constants;
import com.carscompany.config.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

  private final UserDetailsService userDetailsService;

  private final JwtUtils jwtUtils;

  public JwtFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
    this.userDetailsService = userDetailsService;
    this.jwtUtils = jwtUtils;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    final String authorizationHeader = request.getHeader(Constants.AUTHORIZATION_HEADER);

    String username = null;
    String jwt = null;

    if (authorizationHeader != null && authorizationHeader.startsWith(Constants.BEARER)) {
      jwt = authorizationHeader.substring(7);
      try{
        username = jwtUtils.extractUsername(jwt);
      }catch (IllegalArgumentException e){
        logger.warn("JWT Token does not begin with Bearer String");
      } catch (ExpiredJwtException e){
        logger.warn("JWT Token has expired");
      }

    } else {
      logger.warn("JWT Token does not begin with Bearer String");
      chain.doFilter(request, response);
      return;
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      if (jwtUtils.validateToken(jwt, userDetails)) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
    chain.doFilter(request, response);
  }

}
