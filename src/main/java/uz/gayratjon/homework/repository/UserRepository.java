package uz.gayratjon.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.gayratjon.homework.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLogin(String login);    // login bor yoki yo'qligini tekshirib beradi

    User findByLogin(String login);

}
