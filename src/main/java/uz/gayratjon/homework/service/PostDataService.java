package uz.gayratjon.homework.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.gayratjon.homework.entity.PostData;
import uz.gayratjon.homework.model.Post;
import uz.gayratjon.homework.repository.PostDataRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class PostDataService {
    private final PostDataRepository postDataRepository;

    public PostDataService(PostDataRepository postDataRepository) {
        this.postDataRepository = postDataRepository;
    }

    public List<PostData> saveAll(Post[] posts) {

        List<PostData> postDataList = new ArrayList<>();
        for (Post post : posts) {
            PostData postData = new PostData();
            postData.setPostId(post.getId());
            postData.setUserId(post.getUserId());
            postData.setTitle(post.getTitle());
            postData.setBody(post.getBody());

            postDataList.add(postData);
        }
        postDataRepository.saveAll(postDataList);
        return postDataList;
    }

    // Pagination
    @Transactional(readOnly = true)
    public Page<PostData> findAll(Pageable pageable){
        Page<PostData> result = postDataRepository.findAll(pageable);
        return result;
    }
}
