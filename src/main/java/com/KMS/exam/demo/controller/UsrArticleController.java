package com.KMS.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.ArticleService;
import com.KMS.exam.demo.vo.Article;


@Controller
public class UsrArticleController {

	// 인스턴스 변수
	@Autowired
	private ArticleService articleService;

	// 액션메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body ,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int loginedId = (int) session.getAttribute("loginedId");
		
		int id = articleService.writeArticle(title, body, loginedId);

		Article article = articleService.getArticle(id);

		return article;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articleService.getArticles();
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}

		articleService.deleteArticle(id);

		return id + "번 게시물을 삭제했습니다";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}

		articleService.modifyArticle(id, title, body);
		article = articleService.getArticle(id);

		return article;
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return id + "번 게시물은 존재하지 않습니다";
		}

		return article;
	}

}