package uz.gayratjon.homework.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gayratjon.homework.entity.PostData;
import uz.gayratjon.homework.service.PostDataService;


@RestController
@RequestMapping("/api")
public class PostDataResource {
    private final PostDataService postDataService;

    public PostDataResource(PostDataService postDataService) {
        this.postDataService = postDataService;
    }

    @GetMapping("/postdata/paging")
    public ResponseEntity getAllByPaging(Pageable pageable){
        Page<PostData> pages = postDataService.findAll(pageable);
        return ResponseEntity.ok(pages);
    }
    /****************************************************************************
     *              POSTMAN dan Pageable  request yuborish:                     *
     ****************************************************************************
     *  KEY    | VALUE    *                                                     *
     *  __________________*                                                     *
     *  page   |  1       *     1-page ni chiqar                                *
     *  size   |  10      *     har bir page da 10tadan element bo'lsin         *
     *  sort   |  id,asc  *     id bo'yicha ascending order bilan               *
     *                                                                          *
     ****************************************************************************/



}
