package uz.gayratjon.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.gayratjon.homework.entity.Item;

@Repository
    public interface ItemRepository extends JpaRepository<Item, Long> {
}
