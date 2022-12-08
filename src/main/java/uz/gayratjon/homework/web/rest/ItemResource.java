package uz.gayratjon.homework.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.gayratjon.homework.entity.Item;
import uz.gayratjon.homework.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemResource {

    private final ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ResponseEntity create(@RequestBody Item item){
        Item result = itemService.save(item);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/items")
    public ResponseEntity update(@RequestBody Item item){
        if (itemService.findById(item.getId()) == null){
            return ResponseEntity.badRequest().body("Item not found");
        }
        Item result = itemService.save(item);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getOne(@PathVariable Long id){
        if (itemService.findById(id) == null){
            return ResponseEntity.badRequest().build();
        }
        Item result = itemService.findById(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        itemService.delete(id);
        return ResponseEntity.ok("Item deleted !");
    }
}
