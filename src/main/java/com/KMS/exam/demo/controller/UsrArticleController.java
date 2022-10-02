package com.KMS.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.ArticleService;
import com.KMS.exam.demo.service.MemberService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Article;
import com.KMS.exam.demo.vo.Member;
import com.KMS.exam.demo.vo.ResultData;


@Controller
public class UsrArticleController {

	// 인스턴스 변수
	@Autowired
	private ArticleService articleService;
	@Autowired
	private MemberService memberService;
	
	// 액션메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(String title, String body ,HttpServletRequest request) {
		
		int loginedId = Ut.getLoginedId(request);
		
		if(loginedId == 0){
			return ResultData.from("F-1", "로그인 하세요");
		}
		if(Ut.empty(title)) {
			return ResultData.from("F-2", "제목을 입력해주세요");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-3", "내용을 입력해주세요");
		}
		
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
	public ResultData<Article> doDelete(int id, HttpServletRequest request) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		int loginedId = Ut.getLoginedId(request);
		
		if(loginedId == 0) {
			return ResultData.from("F-2", Ut.f("로그인 하지 않았습니다."));
		}
		
		Member member = memberService.getMember(loginedId);
		int loginedLevel = member.getAuthLevel();
		
		if(loginedId != article.getLoginedId() &&  loginedLevel != 7) {
			return ResultData.from("F-3", Ut.f("권한이 없습니다."));
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