package com.example.matine.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    Optional<Content> findContentByContentName(String contentName);

    List<Content> searchContentByContentName(String contentName);

    List<Content> findContentByGenreId(Long genreId);
}
