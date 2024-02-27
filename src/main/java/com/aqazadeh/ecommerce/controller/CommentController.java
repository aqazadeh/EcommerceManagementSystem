package com.aqazadeh.ecommerce.controller;

import com.aqazadeh.ecommerce.dto.request.CreateCommentRequest;
import com.aqazadeh.ecommerce.dto.response.CommentDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 22.02.2024
 * Time: 17:52
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<Void> addComment(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CreateCommentRequest request
    ) {

        commentService.addComment(user, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{commentId}")
    @PreAuthorize("hasRole('USER')")
    private ResponseEntity<Void> updateComment(
            @AuthenticationPrincipal User user,
            @PathVariable Long commentId,
            @RequestPart String message
    ) {

        commentService.updateComment(user, commentId, message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<CommentDto> getById(@PathVariable Long id) {
        CommentDto commentDto = commentService.getById(id);
        return ResponseEntity.ok(commentDto);
    }


    @GetMapping("/product/{productId}/")
    public ResponseEntity<List<CommentDto>> getComments(
            @PathVariable Long productId,
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        List<CommentDto> comments = commentService.getProductComments(productId, page);
        return ResponseEntity.ok(comments);
    }

}
