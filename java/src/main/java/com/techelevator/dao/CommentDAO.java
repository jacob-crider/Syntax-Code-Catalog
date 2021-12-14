package com.techelevator.dao;

import com.techelevator.model.Comment;

import java.security.Principal;
import java.util.List;

public interface CommentDAO {

    void addComment(Comment comment, Principal principal);
    List<Comment> getCommentsByExampleId(Integer exampleId);
}
