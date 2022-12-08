package uz.gayratjon.homework.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.gayratjon.homework.entity.User;
import uz.gayratjon.homework.repository.UserRepository;
import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
// Password encoderni SecurityConfiguration da @bean qilinganligi uchun dependency injection qila olayabmiz

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));   // passwordni olib uni encode qilayabmiz
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public boolean existByLogin(String login){      // bazada login mavjud bo'lsa true qiymat qaytaradi
        return userRepository.existsByLogin(login);
    }


}
