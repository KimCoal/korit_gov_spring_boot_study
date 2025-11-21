package com.korit.korit_gov_spring_boot_study.service;

import com.korit.korit_gov_spring_boot_study.dto.req.AddPostReqDto;
import com.korit.korit_gov_spring_boot_study.dto.resp.*;
import com.korit.korit_gov_spring_boot_study.entity.Post;
import com.korit.korit_gov_spring_boot_study.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public ApiRespDto addPost(AddPostReqDto addPostReqDto) {
        if (postRepository.findPostByTitle(addPostReqDto.getTitle()) != null){
            return new ApiRespDto("failed", "제목이 중복되었습니다.");
        }
        postRepository.addPost(addPostReqDto.toEntity());
        return new ApiRespDto("success", "게시물이 등록되었습니다.");
    }

    public ApiDataRespDto<?> getPostAll() {
        return new ApiDataRespDto<>("success", "전체조회 완료", postRepository.getPostAll());
    }

    public ApiDataRespDto<?> getPostByPostId(Integer postId) {
        Post post = postRepository.findPostByPostId(postId);
        if (post == null) return new ApiDataRespDto<>("failed", "조회된 결과가 없습니다", null);
        return new ApiDataRespDto<>("success", "단건조회를 완료했습니다", post);
    }

    public ApiDataRespDto<?> getPostListByKeyword(String keyword) {
        List<Post> findPost = postRepository.findPostByKeyword(keyword);
        if (findPost == null) return new ApiDataRespDto<>("failed", "조회된 결과가 없습니다", null);
        return new ApiDataRespDto<>("success", "조회 완료", findPost);
    }

}
