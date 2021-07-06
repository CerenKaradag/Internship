package com.example.matine.repository;

import com.example.matine.model.Archive;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ArchiveRepository extends JpaRepository<Archive, Long> {

    List<Archive> findArchiveByUserId(Long userId);


}
