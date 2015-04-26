package com.samsungsds.board.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samsungsds.board.admin.model.BoardAdminService;
import com.samsungsds.board.model.BoardKindDto;

@WebServlet("/admin")
public class BoardAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardAdminService adminService;
	
	public void init() {
		adminService = new BoardAdminService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		execute(request, response);
	}
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		
		String act = request.getParameter("act");
		
		if("boardlist".equals(act)) {
			List<BoardKindDto> list = adminService.getBoardList();
			
			request.setAttribute("boardList", list);
			RequestDispatcher disp = request.getRequestDispatcher("/boardmenu.jsp");
			disp.forward(request, response);				
		} else if("".equals(act)) {
			
		} else if("".equals(act)) {
			
		} else if("".equals(act)) {
			
		}
	}

}

















