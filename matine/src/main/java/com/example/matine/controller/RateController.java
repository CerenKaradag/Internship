package com.example.matine.controller;

import com.example.matine.model.Comment;
import com.example.matine.model.Rate;
import com.example.matine.service.CommentService;
import com.example.matine.service.RateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/matine/rate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RateController {

    private final RateService rateService;

    public RateController(RateService rateService) {
        super();
        this.rateService = rateService;
    }

    @GetMapping(path = "/{contentId}")
    public List<Rate> getRates(@PathVariable("contentId") Long contentId){
        return rateService.getRates(contentId);
    }

    @PostMapping(path = "/{contentId}/{userId}")
    public void addRate(@PathVariable("contentId") Long contentId,
                          @PathVariable("userId") Long userId,
                          @RequestBody Rate rate){
        rateService.addRate(contentId,userId,rate);
    }

}
