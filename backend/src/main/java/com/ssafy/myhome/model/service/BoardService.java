package com.ssafy.myhome.model.service;

import java.util.List;

import com.ssafy.myhome.model.dto.BoardDto;
import com.ssafy.myhome.model.dto.BoardParameterDto;
import com.ssafy.myhome.util.PageNavigation;



public interface BoardService {
	public boolean writeArticle(BoardDto boardDto) throws Exception;
	public List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;
	
	public BoardDto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(BoardDto boardDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
}
