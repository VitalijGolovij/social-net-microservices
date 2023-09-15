package ru.goloviy.profileservice.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.goloviy.profileservice.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerToken = request.getHeader("Authorization");
        if (isValidToken(headerToken)){
            String tokenWithoutBearer = extractTokenFromHeader(headerToken);

            try{
                String claimUsername = jwtUtil.getUsernameClaim(tokenWithoutBearer);
                UserDetails userDetails = userDetailsService.loadUserByUsername(claimUsername);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
                if(SecurityContextHolder.getContext().getAuthentication() == null){
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (JWTVerificationException e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        filterChain.doFilter(request,response);
    }

    private String extractTokenFromHeader(String headerToken) {
        return headerToken.substring(7);
    }

    private boolean isValidToken(String token){
        return token != null && !token.isBlank() && token.startsWith("Bearer ");
    }
}
