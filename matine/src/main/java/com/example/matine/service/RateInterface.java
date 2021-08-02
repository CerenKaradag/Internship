package com.example.matine.service;

import com.example.matine.model.Rate;
import java.util.List;

public interface RateInterface {

    List<Rate> getRates(Long contentId);

    public void addRate(Long contentId, Long userId, Rate rate);
}
