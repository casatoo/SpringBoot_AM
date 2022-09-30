package com.KMS.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.ArticleService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Article;
import com.KMS.exam.demo.vo.ResultData;


@Controller
public class UsrArticleController {

	// 인스턴스 변수
	@Autowired
	private ArticleService articleService;

	// 액션메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(String title, String body ,HttpServletRequest request) {
		
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-1", "내용을 입력해주세요");
		}
		
		HttpSession session = request.getSession();
		int loginedId = (int) session.getAttribute("loginedId");
		
		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, loginedId);
		
		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticle(id);

		return ResultData.newData(writeArticleRd, article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		List<Article> articles = articleService.getArticles();

		return ResultData.from("S-1", "Article List", articles);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Article> doDelete(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		articleService.deleteArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 게시물삭제.", id), article);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		articleService.modifyArticle(id, title, body);
		article = articleService.getArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 게시물수정.", id), article);
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시물입니다.", id), article);
	}

}