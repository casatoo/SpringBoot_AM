package com.KMS.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.KMS.exam.demo.vo.Rq;


@Controller
public class UsrArticleController {

	// 인스턴스 변수
	@Autowired
	private ArticleService articleService;
	
	// 액션메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(String title, String body, int boardId, HttpServletRequest req, Model model) {
		Rq rq = (Rq) req.getAttribute("rq");

		if(Ut.empty(title)) {
			return Ut.jsHistoryBack(Ut.f("제목을 입력해주세요"));
		}
		if(Ut.empty(body)) {
			return Ut.jsHistoryBack(Ut.f("내용을 입력해주세요"));
		}
		ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body, boardId);
		
		int id = (int) writeArticleRd.getData1();
		
		return Ut.jsReplace(Ut.f("%d번 게시물 작성", id),  Ut.f("../article/detail?id=%d", id));
	}

	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model,Integer boardId) {
		if(boardId==null) {
			boardId = 0;
		}
		Rq rq = (Rq) req.getAttribute("rq");
		List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId(),boardId);
		int pageCount = (int) Math.ceil((double)articles.size()/10);
		
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("boardId",boardId);
		model.addAttribute("articles", articles);
		model.addAttribute("rq",rq);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다", id));
		}
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물에 대한 권한이 없습니다.", id));
		}
		articleService.deleteArticle(id);
		return Ut.jsReplace(Ut.f("%d번 게시물을 삭제했습니다", id), "../article/list");
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		if (article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다", id));
		}
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물에 대한 권한이 없습니다.", id));
		}
		if(Ut.empty(title)) {
			return Ut.jsHistoryBack(Ut.f("제목을 입력해주세요"));
		}
		if(Ut.empty(body)) {
			return Ut.jsHistoryBack(Ut.f("내용을 입력해주세요"));
		}
		articleService.modifyArticle(id, title, body);
		return Ut.jsReplace(Ut.f("%d번 게시물을 수정했습니다", id), Ut.f("../article/detail?id=%d", id));

	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		model.addAttribute("article", article);
		return "usr/article/detail";
	}

	
	@RequestMapping("usr/article/write")
	public String articleWriteForm(HttpServletRequest req, Model model, int boardId) {
		model.addAttribute("boardId", boardId);
		return "usr/article/write" ;
	}
	@RequestMapping("usr/article/modify")
	public String articleModifyForm(HttpServletRequest req, Model model,int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		if (article.getMemberId() != rq.getLoginedMemberId()) {
				return rq.jsHistoryBackOnView("권한이 없습니다.");
		}
		model.addAttribute("article", article);
		return "usr/article/modify" ;
	}

}