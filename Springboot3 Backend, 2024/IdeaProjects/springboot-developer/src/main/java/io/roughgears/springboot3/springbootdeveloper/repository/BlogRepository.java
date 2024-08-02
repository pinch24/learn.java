package io.roughgears.springboot3.springbootdeveloper.repository;

import io.roughgears.springboot3.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
