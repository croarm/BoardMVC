package com.samsungsds.board.model;

import java.util.List;
import java.util.Map;

import com.samsungsds.board.util.PageNavigation;

public interface ReboardService {
	
	public int getNextSeq();
	public int writeNewArticle(ReboardDto reboardDto);
	public List<ReboardDto> getArticleList(Map<String, String> map);
	public ReboardDto getArticle(int seq);
	public int writeReplyArticle(ReboardDto reboardDto);
	
	public int modifyArticle(ReboardDto reboardDto);
	public int deleteArticle(int seq);
	public PageNavigation getPageNavigation();
	PageNavigation getPageNavigation(Map<String, String> map);
	
}
