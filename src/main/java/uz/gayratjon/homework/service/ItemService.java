package uz.gayratjon.homework.service;

import org.springframework.stereotype.Service;
import uz.gayratjon.homework.entity.Item;
import uz.gayratjon.homework.repository.ItemRepository;

import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // POST SAVE
    public Item save(Item item){
        return itemRepository.save(item);
    }

    // GET BY ID
    public Item findById(Long id){
        Optional<Item> optional = itemRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    // DELETE
    public void delete(Long id){
        itemRepository.deleteById(id);
    }
}
