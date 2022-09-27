package com.KMS.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.KMS.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {

	public Article writeArticle(String title, String body);

	@Select("SELECT * FROM article WHERE id = #{id}")
	public Article getArticle(int id);
	
	public List<Article> getArticles();

	public void deleteArticle(int id);

	public void modifyArticle(int id, String title, String body);
}
