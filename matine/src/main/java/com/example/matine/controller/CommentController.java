package com.example.matine.controller;

import com.example.matine.model.Comment;
import com.example.matine.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/matine/comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        super();
        this.commentService = commentService;
    }

    @GetMapping(path = "/{contentId}")
    public List<Comment> getComments(@PathVariable("contentId") Long contentId){
        return commentService.getComments(contentId);
    }

    @PostMapping(path = "/{contentId}/{userId}")
    public void addComment(@PathVariable("contentId") Long contentId,
                          @PathVariable("userId") Long userId,
                          @RequestBody Comment comment){
        commentService.addComment(contentId,userId,comment);
    }

    @DeleteMapping(path = "/{contentId}/{userId}")
    public void deleteComment(@PathVariable ("userId") Long userId,
                              @RequestBody Comment comment){
        commentService.deleteComment(userId, comment);
    }
}
