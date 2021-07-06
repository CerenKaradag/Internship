package com.example.matine.content;

import com.example.matine.genre.Genre;
import com.example.matine.model.ReportComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {

        this.contentService = contentService;
    }

    @GetMapping(path = "/matine/{genreName}/content")
    public List<Content> getContents() {
        return contentService.getContents();
    }

    @PostMapping(path = "/matine/{genreName}/content")
    public void createNewContent(@RequestBody Content content){
        contentService.addNewContent(content);
    }

    //yorum bildirme

    @GetMapping(path = "/matine/report/{contentId}/{commentId}" )
    public List<ReportComment> getReportedComments(@PathVariable ("contentId") Long contentId) {
        return contentService.getReportedComments(contentId);
    }

    @PostMapping(path = "/matine/report/{contentId}/{commentId}" )
    public void reportComment(@PathVariable ("contentId") Long contentId,
                              @PathVariable ("commentId") Long commentId,
                              @RequestBody ReportComment reportComment) {
        contentService.reportComment(contentId,commentId,reportComment);
    }

    @DeleteMapping(path = "/matine/report/{contentId}/{commentId}" )
    public void deleteReportedComments(@RequestBody ReportComment reportComment) {
        contentService.deleteReportedComments(reportComment);
    }

    @PostMapping(path = "/matine/profile/archive/{userId}")
    public void addContentToMyArchive(@PathVariable ("userId") Long userId,
                                      @RequestBody Content content) {
        contentService.addContentToMyArchive(userId,content);
    }

    @PostMapping(path = "/matine/search")
    public List<Content> search(@RequestBody Content content){
        return contentService.searchContents(content);
    }




}
