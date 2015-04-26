package com.samsungsds.board.model;

import java.util.List;
import java.util.Map;

import com.samsungsds.board.util.PageConstant;
import com.samsungsds.board.util.PageNavigation;

public class ReboardServiceImpl implements ReboardService {
	
	ReboardDao reboardDao;
	private int lcount;
	private int ncount;
	
	public ReboardServiceImpl() {
		reboardDao = new ReboardDao();
		lcount = PageConstant.LIST_COUNT;
		ncount = PageConstant.NAVIGATION_COUNT;
	}

	@Override
	public int getNextSeq() {
		return reboardDao.getNextSeq();
	}

	@Override
	public int writeNewArticle(ReboardDto reboardDto) {
		return reboardDao.wrtieNewArticle(reboardDto);
	}

	@Override
	public List<ReboardDto> getArticleList(Map<String, String> map) {
		int end = lcount* Integer.parseInt(map.get("pg"));
		int start = end - lcount;
		map.put("start", start + "");
		map.put("end", end + "");
		return reboardDao.getArticleList(map);
	}

	@Override
	public PageNavigation getPageNavigation(Map<String, String> map) {
		PageNavigation navigation = new PageNavigation();
		navigation.setNewArticleCount(reboardDao.getArticleCount(map,"new"));
		int totalArticleCount = reboardDao.getArticleCount(map,"total");
		navigation.setTotalArticleCount(totalArticleCount);
		int totalPageCount= (totalArticleCount +lcount-1)/lcount;
		navigation.setTotalPageCount(totalPageCount);
		int pageNo = Integer.parseInt(map.get("pg"));
		navigation.setPageNo(pageNo);
		navigation.setNowFirst(pageNo <= ncount);
		navigation.setNowEnd((totalPageCount-1)/ncount*ncount < pageNo);
		navigation.setNavigator();
		return navigation;
	}
	@Override
	public ReboardDto getArticle(int seq) {
		ReboardDto reboardDto =  reboardDao.getArticle(seq);
		reboardDao.updateHit(seq);
		return reboardDto;
	}

	@Override
	public int writeReplyArticle(ReboardDto reboardDto) {
		return 0;
	}

	@Override
	public int modifyArticle(ReboardDto reboardDto) {

		return reboardDao.modifyArticle(reboardDto);
	}

	@Override
	public int deleteArticle(int seq) {
		return 0;
	}


	@Override
	public PageNavigation getPageNavigation() {
		// TODO Auto-generated method stub
		return null;
	}

}
