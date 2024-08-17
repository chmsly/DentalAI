import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    
    @Override
    public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws org.springframework.security.core.AuthenticationException {
        try {
            com.example.dentalai.model.User creds = new ObjectMapper().readValue(req.getInputStream(), com.example.dentalai.model.User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new java.util.ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, jakarta.servlet.FilterChain chain, org.springframework.security.core.Authentication auth) throws IOException, jakarta.servlet.ServletException {
        String token = io.jsonwebtoken.Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 864_000_000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, "SecretKeyToGenJWTs".getBytes())
                .compact();
        res.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage());
    }
}
