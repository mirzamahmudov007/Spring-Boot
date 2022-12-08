package uz.gayratjon.homework.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.validity}")
    private Long validityMilliSecond;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct      // ushbu class ishga tushishi bilan 1-o'rinda quyidagi method ishlaydi
    protected void init(){
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, Authentication authentication){
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);     // tokenni ichiga userga tegishli bo'lgan boshqa parametrlarni ham claims.put qilib bervorsa bo'laveradi yana. Masalan: claims.put("email", email);
        Date now = new Date();      // JWT yaratilgan vaqt
        Date validity = new Date(now.getTime() + validityMilliSecond);  // JWT o'lish vaqti
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)     // encode qilish algoritmi -> ES256
                .compact();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUsername(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }



    public boolean validateToken(String token ){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            if (claimsJws.getBody().getExpiration().before(new Date())){        // getExpiration xozirgi vaqtdan oldin bo'lsa
                return false;       // token muddati tugagan bo'lsa false qaytadi
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return true;        // token muddati tugamagan bo'lsa true qaytadi
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
