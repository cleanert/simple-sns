package com.spring.sns.web.controller;


import com.spring.sns.domain.dto.request.PostCreateRequestDTO;
import com.spring.sns.domain.service.PostService;
import com.spring.sns.web.exception.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;


    @PostMapping
    public ResultResponse<Void> create(@RequestBody PostCreateRequestDTO request) {
        postService.create(request.getTitle(), request.getBody(), "");

        return ResultResponse.success(null);
    }
}
