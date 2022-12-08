package uz.gayratjon.homework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import uz.gayratjon.homework.security.JWTConfigure;
import uz.gayratjon.homework.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //  default holatdagi ba'zi xavfsizlik blocklarini ochib chiqish
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
//*************************************************
                .authorizeRequests()
//                .anyRequest().authenticated()     // barcha authanticated bo'lganlarga ruxsat berish
// ADMIN ga   /api/postdata/paging/ url ga ruxsat berish. Oxirida ** qo'yilsa url istalgancha cho'zilsa ham ruxsat beradi
                .antMatchers("/api/postdata/paging/**").hasRole("ADMIN")
// USER ga va ADMIN ga    /api/posts  url ga ruxsat berish
                .antMatchers("/api/posts").hasAnyRole("USER", "ADMIN")
// Barchaga (authenticated bo'lmaganlarga ham) biror linkka ruxsat berish. Masalan: register yoki login etc...
                .antMatchers("/api/students/{id}").permitAll()
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/upload").permitAll()
                .antMatchers("/api/file/upload").permitAll()
                .antMatchers("/api/download/{hashId}").permitAll()
                .antMatchers("/api/api").permitAll()
                .and()
                .httpBasic()
                .and()
                .apply(securityConfigurerAdapter());
    }

    private JWTConfigure securityConfigurerAdapter(){
        return new JWTConfigure(jwtTokenProvider);
    }


}
