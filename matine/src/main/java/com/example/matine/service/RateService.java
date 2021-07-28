
package com.example.matine.service;

import com.example.matine.model.Comment;
import com.example.matine.model.Rate;
import com.example.matine.repository.RateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    private final RateRepository rateRepository;

    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public List<Rate> getRates(Long contentId) {
        return rateRepository.findByContentId(contentId);
    }


    @Transactional
    public void addRate(Long contentId, Long userId, Rate rate) {

        rate.setUserId(userId);
        rate.setContentId(contentId);
        rateRepository.save(rate);
        System.out.println(rate);
    }


}
