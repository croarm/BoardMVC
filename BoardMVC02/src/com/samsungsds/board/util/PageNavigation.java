package com.samsungsds.board.util;

public class PageNavigation {
	private boolean nowFirst = false;
	private boolean nowEnd = false;
	private int newArticleCount;
	private int totalArticleCount;
	private int totalPageCount;
	private int pageNo;
	private String navigator;

	public boolean isNowFirst() {
		return nowFirst;
	}

	public void setNowFirst(boolean nowFirst) {
		this.nowFirst = nowFirst;
	}

	public boolean isNowEnd() {
		return nowEnd;
	}

	public void setNowEnd(boolean nowEnd) {
		this.nowEnd = nowEnd;
	}

	public int getNewArticleCount() {
		return newArticleCount;
	}

	public void setNewArticleCount(int newArticleCount) {
		this.newArticleCount = newArticleCount;
	}

	public int getTotalArticleCount() {
		return totalArticleCount;
	}

	public void setTotalArticleCount(int totalArticleCount) {
		this.totalArticleCount = totalArticleCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getNavigator() {
		return navigator;
	}

	public void setNavigator() {
		int ncount = PageConstant.NAVIGATION_COUNT;
		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append("<table cellpadding='0' cellspacing='0' border='0'>\n");
		sbHtml.append(" <tr>\n");
		if (this.isNowFirst()) {
			sbHtml.append("  <td><font color='#999999'>\n");
			sbHtml
					.append("   <img src='/boardmvc/img/board/icon_prev02.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'><a href='javascript:goPage(1);'>최신목록</a>\n");
			sbHtml
					.append("   <img src='/boardmvc/img/board/icon_prev01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'>\n");
			sbHtml.append("   이전</font>\n");
		} else {
			sbHtml.append("  <td>\n<a href='javascript:goPage(1)'>");
			sbHtml
					.append("   <img src='/boardmvc/img/board/icon_prev02.gif' width='7' height='11' border='0' align='absmiddle' hspace='3'>최신목록 </a>\n");
			sbHtml.append("   <a href='javascript:goPage(" +((pageNo-1)/ncount*ncount) + ")'>");
			sbHtml
					.append("   <img src='/boardmvc/img/board/icon_prev01_dim.gif' width='3' height='11' border='0' align='absmiddle' hspace='3'>\n");
			sbHtml.append("   이전</a>");
		}
		sbHtml.append("  \n</td>\n");
		sbHtml.append("  <td style='padding: 0 5 0 5'>\n");
		sbHtml
				.append("   <table cellpadding='0' cellspacing='0' border='0'>\n");
		sbHtml.append("    <tr>\n");
		sbHtml
				.append("     <td width='1' nowrap><img src='/boardmvc/img/board/n_tab.gif' width='1'");
		sbHtml.append(" height='11' border='0' align='absmiddle'><br>");
		sbHtml.append("     </td>\n");
		int iStartPage=(pageNo-1)/ncount*ncount+1;
		int iEndPage=iStartPage+ncount-1;
		iEndPage = iEndPage>totalPageCount?totalPageCount:iEndPage;
		for (int i = iStartPage; i <= iEndPage; i++) {
			if (pageNo == i) {
				sbHtml
						.append("     <td style='padding:0 7 0 7;' nowrap><font class='text_acc_02'><b>"
								+ i + "</b></font></td>\n");
				sbHtml
						.append("     <td width='1' nowrap><img src='/boardmvc/img/board/n_tab.gif' width='1'");
				sbHtml
						.append(" height='11' border='0' align='absmiddle'><br>\n");
			} else {
				sbHtml
						.append("     <td style='padding:0 7 0 7;' nowrap><a href='javascript:goPage(" + i + ")'>" + i + "</td>\n");
				sbHtml
						.append("     <td width='1' nowrap><img src='/boardmvc/img/board/n_tab.gif' width='1'");
				sbHtml
						.append(" height='11' border='0' align='absmiddle'><br>\n");
			}
		}
		sbHtml.append("     </td>\n");
		sbHtml.append("    </tr>\n");
		sbHtml.append("   </table>\n");
		sbHtml.append("  </td>\n");
		sbHtml.append("  <td>\n");
		if (this.isNowEnd()) {
			sbHtml.append("   <font color='#999999'>다음<img");
			sbHtml
					.append("   src='/boardmvc/img/board/icon_next01_dim.gif' width='3' height='11'");
			sbHtml.append(" border='0' align='absmiddle' hspace='3'> \n");
			sbHtml
					.append("   끝목록<img src='/boardmvc/img/board/icon_next02_dim.gif' width='7' height='11'");
			sbHtml.append(" border='0' align='absmiddle' hspace='3'></font>\n");
		} else {
			sbHtml.append("   <a href='javascript:goPage(" + ((pageNo+ncount-1)/ncount*ncount+1) + ")'>다음<img");
			sbHtml
					.append(" src='/boardmvc/img/board/icon_next01_dim.gif' width='3' height='11'");
			sbHtml.append(" border='0' align='absmiddle' hspace='3'></a>\n");
			sbHtml
					.append("   <a href='javascript:goPage("
							+ totalPageCount
							+ ")'>끝목록<img src='/boardmvc/img/board/icon_next02_dim.gif' width='7' height='11'");
			sbHtml.append(" border='0' align='absmiddle' hspace='3'>\n");
		}

		sbHtml.append("  </td>\n");
		sbHtml.append(" </tr>\n");
		sbHtml.append("</table>\n");	
		this.navigator = sbHtml.toString();
	}

}
