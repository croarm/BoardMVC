package com.samsungsds.board.admin.model;

import java.util.List;

import com.samsungsds.board.model.BoardKindDto;

public class BoardAdminService {
	
	BoardAdminDao adminDao;
	
	public BoardAdminService() {
		adminDao = new BoardAdminDao();
	}

	public List<BoardKindDto> getBoardList() {
		return adminDao.getBoardList();
	}

}
