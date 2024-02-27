package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.request.CreateCommentRequest;
import com.aqazadeh.ecommerce.dto.response.CommentDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.CommentMapper;
import com.aqazadeh.ecommerce.model.Comment;
import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.repository.CommentRepository;
import com.aqazadeh.ecommerce.service.CommentService;
import com.aqazadeh.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 22.02.2024
 * Time: 18:27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CommentMapper commentMapper;

    private final ProductService productService;


    @Value("${pagination.limit}")
    private Integer pageLimit;

    @Override
    public void addComment(User user, CreateCommentRequest request) {
        Product product = productService.findById(request.productId());
        Comment comment = Comment.builder()
                .product(product)
                .message(request.message())
                .build();
        if (request.parentId() != null) {
            Comment parent = findById(request.parentId());
            comment.setParent(parent);
        }
        repository.save(comment);

    }

    @Override
    public void updateComment(User user, Long commentId, String message) {
        Comment comment = findById(commentId);
        if (!comment.getUser().equals(user))
            throw new ApplicationException((ExceptionType.COMMENT_NOT_FOUND));

        comment.setMessage(message);
        repository.save(comment);
    }

    @Override
    public CommentDto getById(Long commentId) {
        Comment comment = findById(commentId);
        return commentMapper.toDto(comment);
    }

    @Override
    public Comment findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.COMMENT_NOT_FOUND));
    }

    @Override
    public List<CommentDto> getProductComments(Long productId, Integer page) {
        Pageable pageable = PageRequest.of(page, pageLimit);
        Product product = productService.findById(productId);
        List<Comment> comments = repository.findByProductAndParentIsNull(product, pageable);
        return comments.stream().map(commentMapper::toDto).toList();
    }
}
