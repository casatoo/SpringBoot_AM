package com.KMS.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.service.ArticleService;
import com.KMS.exam.demo.service.BoardService;
import com.KMS.exam.demo.util.Ut;
import com.KMS.exam.demo.vo.Article;
import com.KMS.exam.demo.vo.Board;
import com.KMS.exam.demo.vo.ResultData;
import com.KMS.exam.demo.vo.Rq;


@Controller
public class UsrArticleController {

	// 인스턴스 변수
	@Autowired
	private ArticleService articleService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private Rq rq;
	
	// 액션메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(String title, String body, Integer boardId, Model model) {
		if(boardId == null) {
			return Ut.jsHistoryBack(Ut.f("게시판을 선택해주세요"));
		}
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
	public String showList( Model model, @RequestParam(defaultValue = "1")Integer boardId,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "")String searchWord,@RequestParam(defaultValue = "") String searchFrom) {
		if(boardId == null) {
			return rq.jsHistoryBackOnView("지정되지 않은 게시판");
		}
		Board board = boardService.getBoardById(boardId);
		if(board == null) {
			return rq.jsHistoryBackOnView("게시판이 존재하지 않습니다.");
		}
		int itemsInAPage = 10;
		int limitFrom = (page - 1) * itemsInAPage;
		
		List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId(),boardId,limitFrom,itemsInAPage, searchWord, searchFrom);
		int getTotalArticle = articleService.getTotalArticle(boardId, searchWord, searchFrom);
		int pageCount = (int) Math.ceil((double)getTotalArticle/itemsInAPage);
		
		model.addAttribute("searchFrom",searchFrom);
		model.addAttribute("searchWord",searchWord);
		model.addAttribute("getTotalArticle",getTotalArticle);
		model.addAttribute("page",page);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("boardId",boardId);
		model.addAttribute("board", board);
		model.addAttribute("articles", articles);
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id, int boardId) {

		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물은 존재하지 않습니다", id));
		}
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack(Ut.f("%d번 게시물에 대한 권한이 없습니다.", id));
		}
		articleService.deleteArticle(id);
		return Ut.jsReplace(Ut.f("%d번 게시물을 삭제했습니다", id), Ut.f("../article/list?boardId=%d&page=1",boardId));
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
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
	public String showDetail( Model model, int id) {
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		model.addAttribute("article", article);
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/incresedHit")
	@ResponseBody
	public ResultData<Integer> incresedHit(int id) {
		ResultData<Integer> incresedHitRd = articleService.incresedHit(id);

		if (incresedHitRd.isFail()) {
			return incresedHitRd;
		}
		ResultData<Integer> rd = ResultData.newData(incresedHitRd, "hitCount", articleService.getArticleHitCount(id));
		rd.setData2("id", id);
		
		return rd;

	}
	
	@RequestMapping("/usr/article/reactionPoint")
	@ResponseBody
	public String reactionPoint(int relId, int memberId, int point) {
		ResultData<Integer> reactionPointRd = articleService.reactionPoint(relId,memberId,point);
		
		return Ut.jsHistoryBack(Ut.f("게시물 추천이 완료되었습니다."));
	}
	
	@RequestMapping("usr/article/write")
	public String articleWriteForm(HttpServletRequest req, Model model) {
		return "usr/article/write" ;
	}
	@RequestMapping("usr/article/modify")
	public String articleModifyForm( Model model,int id) {
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		if (article.getMemberId() != rq.getLoginedMemberId()) {
				return rq.jsHistoryBackOnView("권한이 없습니다.");
		}
		model.addAttribute("article", article);
		return "usr/article/modify" ;
	}
}