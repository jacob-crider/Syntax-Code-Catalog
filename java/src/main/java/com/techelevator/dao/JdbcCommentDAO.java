package com.techelevator.dao;


import com.techelevator.model.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCommentDAO implements CommentDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcCommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addComment(Comment comment, Principal principal) {
        String sql = "INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, ?, ?, ?)";

        jdbcTemplate.update(sql, comment.getDescription(), comment.getExampleId(), principal.getName());
    }

    @Override
    public List<Comment> getCommentsByExampleId(Integer exampleId) {
        List<Comment> comments = new ArrayList<>();

        String sql = "SELECT id, description, example_comment_id, username FROM comments WHERE example_comment_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, exampleId);
        while (results.next()) {
            Comment comment = new Comment();
            comment.setDescription(results.getString("description"));
            comment.setExampleId(results.getInt("example_comment_id"));
            comment.setId(results.getInt("id"));
            comment.setUsername(results.getString("username"));
            comments.add(comment);
        }
        return comments;
    }
}
