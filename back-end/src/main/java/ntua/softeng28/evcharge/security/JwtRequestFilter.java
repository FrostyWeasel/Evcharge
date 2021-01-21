package ntua.softeng28.evcharge.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    //TODO: Uncomment
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("X-OBSERVATORY-AUTH");

        // String username = null;

        // if(token != null){
        //     username = jwtTokenUtil.getUsernameFromToken(token);
        // }

        // if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
        //     UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(username);

        //     if(Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, userDetails)) && userDetails.isCredentialsNonExpired()){
        //         UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
        //             new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    
        //             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        //             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //     }
        // }

        filterChain.doFilter(request, response);
    }
}
