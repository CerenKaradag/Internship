package com.example.matine.repository;

import com.example.matine.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {

    Optional<Rate> findByRate(Integer rate);

    Optional<Rate> findByUserId(Long userId);

    List<Rate> findByRateId(Long rateId);

    List<Rate> findByContentId(Long contentId);
}
