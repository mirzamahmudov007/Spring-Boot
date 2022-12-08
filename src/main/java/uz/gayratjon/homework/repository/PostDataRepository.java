package uz.gayratjon.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.gayratjon.homework.entity.PostData;

@Repository
public interface PostDataRepository extends JpaRepository<PostData, Long> {
}
