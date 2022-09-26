package com.KMS.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.KMS.exam.demo.vo.Article;

@Controller
public class UsrArticleController {

	private int lastArticleId;
	private List<Article> articles;

	public UsrArticleController() {
		lastArticleId = 0;
		articles = new ArrayList<>();

		makeTestData();
	}
    private Article getArticle(int id){
        for(Article article : articles){
            if(article.getId()==id){
                return article;
            }
        }
        return null;
    }
    private void deleteArticle(int id) {
    	Article article = getArticle(id);
    	
    	articles.remove(article);
    }

	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			int id = lastArticleId + 1;
			String title = "제목 " + i;
			String body = "내용 " + i;

			writeArticle(title, body);
		}
	}
	
	private Article writeArticle(String title, String body) {
		int id = lastArticleId + 1;
		Article article = new Article(id, title, body);
		return article;
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = lastArticleId + 1;
		Article article = writeArticle(title, body);
		
		return article;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articles;
	}
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		Article article = getArticle(id);
		if(article == null) {
			return id+"번 게시물은 존재하지 않습니다.";
		}
		deleteArticle(id);
		return id+"번 게시물을 삭제했습니다.";
	}
	
}