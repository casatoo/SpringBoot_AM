package com.KMS.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ResultData<Article> doAdd(String title, String body, HttpSession httpSession) {
		
		
		if(SessionController.isLogined(httpSession)){
			return ResultData.from("F-1", "로그인 하세요");
		}
		if(Ut.empty(title)) {
			return ResultData.from("F-2", "제목을 입력해주세요");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-3", "내용을 입력해주세요");
		}
		
		int loginedId = SessionController.getLoginedId(httpSession);
		
		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, loginedId);
		
		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticle(id);

		return ResultData.newData(writeArticleRd,"article", article);
	}

	@RequestMapping("/usr/article/getArticles")
	public String showList(Model model) {
	    
		List<Article> articles = articleService.getArticles();
		Member loginMember = SessionController.loginMember;
		
    	model.addAttribute("loginMember",loginMember);
		model.addAttribute("articles",articles);
		
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/doDelete")
	public ResultData<Integer> doDelete(int id, HttpSession httpSession) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id),"id", id);
		}
		
		if(SessionController.isLogined(httpSession)) {
			return ResultData.from("F-2", Ut.f("로그인 하지 않았습니다."));
		}
		
		int loginedId = SessionController.getLoginedId(httpSession);
		int loginedLevel = SessionController.getLoginedLevel(httpSession);
		
		if(loginedId != article.getLoginedId() &&  loginedLevel != 7) {
			return ResultData.from("F-3", Ut.f("권한이 없습니다."));
		}

		articleService.deleteArticle(id);
		return ResultData.from("S-1", Ut.f("%d번 게시물삭제.", id), "id", id);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body, HttpSession httpSession) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id),"id",id);
		}
		
		if(SessionController.isLogined(httpSession)) {
			return ResultData.from("F-2", Ut.f("로그인 하지 않았습니다."));
		}
		
		if(SessionController.authorization(httpSession, article.getLoginedId())) {
			return ResultData.from("F-3", Ut.f("권한이 없습니다."));
		}

		articleService.modifyArticle(id, title, body);
		article = articleService.getArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 게시물수정.", id),"article", article);
	}

	@RequestMapping("/usr/article/getArticle")
	public Object getArticle(int id, Model model) {
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		Member loginMember = SessionController.loginMember;
		
    	model.addAttribute("loginMember",loginMember);
		model.addAttribute("article",article);
		
		return "usr/article/detail";
	}

}