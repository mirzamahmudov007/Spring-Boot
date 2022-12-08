package uz.gayratjon.homework.web.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gayratjon.homework.security.JwtTokenProvider;
import uz.gayratjon.homework.web.rest.vm.LoginVM;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserJWTController {
    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserJWTController(JwtTokenProvider jwtTokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity authorize(@Valid @RequestBody LoginVM loginVM){
//@Valid RequestBody da kelayotgan loginVM ni tekshiradi. LoginVM class da login va password @NotNull belgilangan. Agar login yoki password null kelsa @Valid error qaytarib yuboradi
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVM.getLogin(), loginVM.getPassword());
// user login va parolini UsernamePasswordAuthenticationToken ga tekshirish uchun berib yubordik. DB mavjud bo'lsa success qaytadi
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken); // authentication qilish
        // token yaratishga tayyor
        SecurityContextHolder.getContext().setAuthentication(authentication);   // user ga tegishli data larni SecurityContextHolder da ushlab turadi
        String jwt = jwtTokenProvider.createToken(loginVM.getLogin(), authentication);  // jwt token hosil qilish

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);


        return new ResponseEntity(new JWTToken(jwt), headers, HttpStatus.OK);
    }

    static class JWTToken{
        private String idToken;

        public JWTToken(String idToken) {
            this.idToken = idToken;
        }

        public String getIdToken() {
            return idToken;
        }

        public void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }


}
