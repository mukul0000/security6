package com.example.spring.Security.security.filter;

import com.example.spring.Security.security.config.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor //for constraction injection
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, //it is client request
           @NonNull HttpServletResponse response, // and it is response if we want to change, so we can change from here
            //it is the same response which will be consumed by controller
           @NonNull  FilterChain filterChain
    ) throws ServletException, IOException {
       //1. first we are check weather we have token or not
        final String authHeader = request.getHeader("Authorization");

        final String jwt;
        final String userEmail;
        //2. now we are check weather we have token or not
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response); // so here we are calling next filter
            // return because token don't have "Bearer " so we will not allow to persue
            return;
        }

        jwt  = authHeader.substring(7); //now we have token without "Bearer "
//        userEmail = // todo extract the userEmail from Jwt

       userEmail = jwtService.extractUsername(jwt);

       if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
//           => it will come inside if user have email but still securityContextstill null
//           1.1 . so we get the user from db
//           1.2 . then we check weather is valid or not
//           1.3 . then update the Security context
           UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);


           if(jwtService.isTokenValid(jwt,userDetails)){
               UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                 userDetails,
                 null,
                 userDetails.getAuthorities()
               );
               authToken.setDetails(
                       new WebAuthenticationDetailsSource().buildDetails(request)
               );
               SecurityContextHolder.getContext().setAuthentication(authToken);
           }

       }
        filterChain.doFilter(request,response);




    }



}
