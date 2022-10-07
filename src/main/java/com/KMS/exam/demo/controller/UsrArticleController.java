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
	public String doAdd(String title, String body, HttpSession httpSession, Model model) {
		ResultData resultRd;
		
    	
		if(SessionController.isLogined(httpSession)){
			resultRd = ResultData.from("F-1", "로그인 하세요");
			model.addAttribute("resultRd",resultRd);
			return "usr/member/loginForm";
		}
		if(Ut.empty(title)) {
			resultRd = ResultData.from("F-2", "제목을 입력해주세요");
			model.addAttribute("resultRd",resultRd);
			return "usr/article/writeForm" ;
		}
		if(Ut.empty(body)) {
			resultRd = ResultData.from("F-3", "내용을 입력해주세요");
			model.addAttribute("resultRd",resultRd);
			return "usr/article/writeForm" ;
		}
		Member loginMember = SessionController.loginMember;
		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body, loginMember.getId());
		resultRd = ResultData.from(writeArticleRd.getResultCode(),writeArticleRd.getMsg());
		List<Article> articles = articleService.getArticles();
		
    	model.addAttribute("loginMember",loginMember);
		model.addAttribute("articles",articles);
    	model.addAttribute("resultRd",resultRd);

		return "/usr/article/list";
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
	public String doDelete(int id, HttpSession httpSession, Model model) {
		ResultData resultRd;
		Article article = articleService.getArticle(id);

		if(SessionController.isLogined(httpSession)) {
			resultRd = ResultData.from("F-2", Ut.f("로그인 하지 않았습니다."));
			model.addAttribute("resultRd",resultRd);
			return "usr/member/loginForm";
		}
		
		int loginedId = SessionController.getLoginedId(httpSession);
		int loginedLevel = SessionController.getLoginedLevel(httpSession);
		
		if(SessionController.authorization(httpSession, article.getLoginedId())) {
			resultRd = ResultData.from("F-3", Ut.f("권한이 없습니다."));
			model.addAttribute("resultRd",resultRd);
			return "usr/home/main";
		}

		articleService.deleteArticle(id);
		resultRd = ResultData.from("S-1", Ut.f("%d번 게시물삭제.", id), "id", id);
		List<Article> articles = articleService.getArticles();
		Member loginMember = SessionController.loginMember;
		
    	model.addAttribute("loginMember",loginMember);
		model.addAttribute("articles",articles);
		model.addAttribute("resultRd",resultRd);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/doModify")
	public String doModify(int id, String title, String body, HttpSession httpSession, Model model) {
		ResultData resultRd;
		Article article = articleService.getArticle(id);
		
		if(SessionController.isLogined(httpSession)) {
			resultRd = ResultData.from("F-2", Ut.f("로그인 하지 않았습니다."));
			model.addAttribute("resultRd",resultRd);
			return "usr/member/loginForm";
		}
		
		if(SessionController.authorization(httpSession, article.getLoginedId())) {
			resultRd = ResultData.from("F-3", Ut.f("권한이 없습니다."));
			model.addAttribute("resultRd",resultRd);
			return "usr/home/main";
		}

		articleService.modifyArticle(id, title, body);
		article = articleService.getArticle(id);
		resultRd = ResultData.from("S-1", Ut.f("%d번 게시물수정.", id),"article", article);
		List<Article> articles = articleService.getArticles();
		Member loginMember = SessionController.loginMember;
		
    	model.addAttribute("loginMember",loginMember);
		model.addAttribute("articles",articles);
		model.addAttribute("resultRd",resultRd);
		
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/getArticle")
	public String getArticle(int id, Model model) {
		Article article = articleService.getArticle(id);
		Member loginMember = SessionController.loginMember;
		
    	model.addAttribute("loginMember",loginMember);
		model.addAttribute("article",article);
		
		return "usr/article/detail";
	}
	
	@RequestMapping("usr/article/writeForm")
	public String articleWriteForm(Model model) {
		Member loginMember = SessionController.loginMember;
		model.addAttribute("loginMember",loginMember);
		return "usr/article/writeForm" ;
	}
	@RequestMapping("usr/article/modifyForm")
	public String articleModifyForm(Model model) {
		Member loginMember = SessionController.loginMember;
		model.addAttribute("loginMember",loginMember);
		return "usr/article/modifyForm" ;
	}

}