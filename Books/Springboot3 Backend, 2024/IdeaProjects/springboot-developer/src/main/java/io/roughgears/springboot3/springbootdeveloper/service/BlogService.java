package io.roughgears.springboot3.springbootdeveloper.service;

import io.roughgears.springboot3.springbootdeveloper.domain.Article;
import io.roughgears.springboot3.springbootdeveloper.dto.AddArticleRequest;
import io.roughgears.springboot3.springbootdeveloper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
