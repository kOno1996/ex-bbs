package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

@Service
@Transactional
public class CommentService {
	@Autowired
	CommentRepository commentRepository;
	public List<Comment> findByArticleId(int articleId){
		return commentRepository.findByArticleId(articleId);
	}
	
	public void insert(Comment comment) {
		commentRepository.insert(comment);
	}
	
	public void deleteComment(int articleId) {
		commentRepository.deleteByArticleId(articleId);
	}
}
