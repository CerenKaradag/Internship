
package com.example.matine.service;

import com.example.matine.model.Comment;
import com.example.matine.model.Rate;
import com.example.matine.repository.RateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// Controller sınıfında tanımlanan fonksiyonların içerikleri ve çalışmasını sağlayan servis sınıfı

@Service
public class RateService {

    private final RateRepository rateRepository;

    public RateService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    // İlgili içeriğe ait sistemde kayıtlı olan bütün oylara erişilen fonksiyon
    public List<Rate> getRates(Long contentId) {
        return rateRepository.findByContentId(contentId);
    }

    // Kulanıcı istediği içeriğe istediği oyu verebilir, ve bu fonksiyon ile oy kaydedilir
    @Transactional
    public void addRate(Long contentId, Long userId, Rate rate) {

        rate.setUserId(userId);
        rate.setContentId(contentId);
        rateRepository.save(rate);

    }


}
