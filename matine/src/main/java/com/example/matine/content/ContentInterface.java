package com.example.matine.content;

import com.example.matine.exception.ApiRequestException;
import com.example.matine.model.Archive;
import com.example.matine.model.ReportComment;

import java.util.List;

public interface ContentInterface {

    List<Content> getContents ();

    List<Content> getContentWithGenreId (Long genreId);

    void addNewContent (Content content) throws ApiRequestException;

    void reportComment (Long contentId, ReportComment reportComment) throws ApiRequestException;

    List<ReportComment> getReportedComments (Long contentId) throws ApiRequestException;

    List<ReportComment> getAllReportedComments ();

    void deleteReportedComments (Long commentId);

    void addContentToMyArchive (Long userId, Content content) throws ApiRequestException;

    void deleteContentFromMyArchive (Long userId, Archive archive);

    List<Content> searchContents (Content content);

}
