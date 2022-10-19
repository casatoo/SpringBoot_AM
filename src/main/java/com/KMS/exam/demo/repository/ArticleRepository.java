package com.KMS.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.KMS.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public void writeArticle(int memberId, String title, String body, int boardId);

	public Article getForPrintArticle(int id);

	public List<Article> getArticles(int boardId, int limitFrom, int itemsInAPage, String searchItem, String searchFrom);
	
	public int getTotalArticle(int boardId, String searchItem,String searchFrom);

	public void deleteArticle(int id);

	public void modifyArticle(int id, String title, String body);

	public int getLastInsertId();

}