package com.samsungsds.board.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.samsungsds.board.model.*;
import com.samsungsds.board.util.*;
import com.samsungsds.member.model.MemberDto;

@WebServlet("/reboard")
public class ReboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ReboardService reboardService;

	public void init() {
		reboardService = new ReboardServiceImpl();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		execute(request, response);
	}

	private void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();

		String act = StringCheck.nullToBlank(request.getParameter("act"));
		int bcode = StringCheck.stringToZero(request.getParameter("bcode"));
		int pg = StringCheck.stringToOne(request.getParameter("pg"));
		String key = StringCheck.nullToBlank(request.getParameter("key"));
		String word = Encoder.isoToEuc((request.getParameter("word")));
		System.out.println("key == "+key+"\tword = "+word);

		String queryString = "?bcode=" + bcode + "&pg=" + pg + "&key=" + key	+ "&word=" + UrlFormat.eucEncoding(word);
		
		System.out.println("서블릿 Query String ::::: " + queryString);

		String path = "/index.jsp";
		boolean flag = true;
		if (act.equals("mvnew")) {
			path = "/board/write.jsp" + queryString;
		} else if (act.equals("newwrite")) {

			HttpSession session = request.getSession();
			MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
			int cnt = 0;
			int seq = 0;

			seq = reboardService.getNextSeq();

			ReboardDto reboardDto = new ReboardDto();
			reboardDto.setSeq(seq);
			reboardDto.setName(memberDto.getName());
			reboardDto.setId(memberDto.getId());
			reboardDto.setEmail(memberDto.getEmail1() + "@"
					+ memberDto.getEmail2());
			reboardDto.setSubject(request.getParameter("subject"));
			reboardDto.setContent(request.getParameter("content"));
			reboardDto.setBcode(bcode);
			reboardDto.setRef(seq);

			cnt = reboardService.writeNewArticle(reboardDto);

			if (cnt == 0) {
				path = "/board/writeFail.jsp" + queryString;
			} else {
				path = "/board/writeOk.jsp" + queryString + "&seq=" + seq;
			}

		} else if (act.equals("articleview")) {
			int seq = StringCheck.stringToZero(request.getParameter("seq"));

			ReboardDto reboardDto = reboardService.getArticle(seq);

			request.setAttribute("reboardArticle", reboardDto);
			path = "/board/view.jsp" + queryString;
			flag = false;
		} else if (act.equals("articlelist")) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("bcode", bcode + "");
			map.put("pg", pg + "");
			map.put("key", key);
			map.put("word", word);
			// 글목록 출력.
			List<ReboardDto> articlelist = reboardService.getArticleList(map);

			// paging 처리
			PageNavigation navigation = reboardService.getPageNavigation(map);

			request.setAttribute("articleList", articlelist);
			request.setAttribute("pageNavigation", navigation);
			path = "/board/list.jsp" + queryString;
			flag = false;
		} else if (act.equals("modify")) {
			int seq = StringCheck.stringToZero(request.getParameter("seq"));
			ReboardDto reboardDto = reboardService.getArticle(seq);
			request.setAttribute("reboardArticle", reboardDto);

			path = "/board/modify.jsp" + queryString + "&seq=" + seq;

		} else if (act.equals("")) {

		} else if (act.equals("")) {

		} else if (act.equals("")) {

		} else if (act.equals("")) {

		} else if (act.equals("")) {

		}

		if (flag) {
			response.sendRedirect(root + path);
		} else {
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		}

	}

}
