package uz.gayratjon.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.gayratjon.homework.entity.FileStorage;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
        FileStorage findByHashId(String hashId);
}
