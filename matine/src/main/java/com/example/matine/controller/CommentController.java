package com.example.matine.controller;

import com.example.matine.model.Comment;
import com.example.matine.service.CommentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/matine/comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {

    // Yorumların HTTP requestlerinin gerçekleştirildiği sınıf

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        super();
        this.commentService = commentService;
    }

    // Herhangi bir içeriğe ait bütün yorumlara ulaşılması
    @GetMapping(path = "/{contentId}")
    public List<Comment> getComments(@PathVariable("contentId") Long contentId){
        return commentService.getComments(contentId);
    }

    // Herhangi bir içeriğe yorum eklenmesi
    @PostMapping(path = "/{contentId}/{userId}")
    public void addComment(@PathVariable("contentId") Long contentId,
                          @PathVariable("userId") Long userId,
                          @RequestBody Comment comment){
        commentService.addComment(contentId,userId,comment);
    }

    // Herhangi bir içeriğe ait yorumun silinmesi
    @DeleteMapping(path = "/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId){
        System.out.println(commentId);
        commentService.deleteComment(commentId);
    }
}
