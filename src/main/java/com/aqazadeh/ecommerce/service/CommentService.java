package com.aqazadeh.ecommerce.service;

import com.aqazadeh.ecommerce.dto.request.CreateCommentRequest;
import com.aqazadeh.ecommerce.dto.response.CommentDto;
import com.aqazadeh.ecommerce.model.Comment;
import com.aqazadeh.ecommerce.model.User;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 22.02.2024
 * Time: 17:53
 */

public interface CommentService {
    void addComment(User user, CreateCommentRequest request);

    void updateComment(User user, Long commentId, String message);

    CommentDto getById(Long commentId);

    Comment findById(Long id);

    List<CommentDto> getProductComments(Long productId, Integer page);
}
