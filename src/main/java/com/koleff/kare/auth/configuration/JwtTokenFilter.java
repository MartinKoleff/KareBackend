package com.koleff.kare.auth.configuration;

import com.koleff.kare.auth.service.UserService;
import com.koleff.kare.auth.service.JWTTokenService;
import com.koleff.kare.common.error.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JWTTokenService jwtTokenService;

    private final UserService userService;

    @Autowired
    public JwtTokenFilter(@Lazy JWTTokenService jwtTokenService,
                          @Lazy UserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }


    /**
     * Authorizes JWT token
     * - Filters header
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String header = request.getHeader("Authorization");

        //Header validation
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtTokenService.extractUsername(token);

            //Username validation
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                //Fetch the user from DB
                UserDetails userDetails = userService.loadUserByUsername(username);

                //Token validation
                if (jwtTokenService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}