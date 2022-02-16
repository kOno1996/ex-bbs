package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.service.ArticleService;
import com.example.service.CommentService;


@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CommentService commentService;
	
	@ModelAttribute
	private ArticleForm articleFormsetUpForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	private CommentForm commentFormsetUpForm() {
		return new CommentForm();
	}
	
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleService.showList();
		for(Article article: articleList) {
			article.setCommentList(commentService.findByArticleId(article.getId()));
		}
		model.addAttribute("articleList", articleList);
		return "article";
	}
	
	@RequestMapping("/insert-article")
	public String insertArticle(ArticleForm form, Model model) {
		Article article = new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleService.insert(article);
		return index(model);
	}
	
	@RequestMapping("/insert-comment")
	public String insertComment(CommentForm form, Integer articleId, Model model) {
		Comment comment = new Comment();
		comment.setName(form.getName());
		comment.setContent(form.getContent());
		comment.setArticleId(articleId);
		commentService.insert(comment);
		return index(model);
	}
	
	@RequestMapping("/delete-article")
	public String deleteArticle(int id, Model model) {
		commentService.deleteComment(id);
		articleService.deleteArticle(id);
		
		return index(model);
	}
	
}
