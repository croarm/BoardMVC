package com.samsungsds.board.admin.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.samsungsds.board.model.BoardKindDto;
import com.samsungsds.board.util.db.DBClose;
import com.samsungsds.board.util.db.DBConnection;

public class BoardAdminDao {

	public List<BoardKindDto> getBoardList() {
		List<BoardKindDto> list = new ArrayList<BoardKindDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			String sql = "select l.bcode, c.cname, c.ccode, l.bname, l.btype \n";
			sql += "from board_list l, board_category c \n";
			sql += "where l.ccode=c.ccode \n";
			sql += "order by c.ccode \n";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardKindDto dto = new BoardKindDto();
				dto.setBcode(rs.getInt("bcode"));
				dto.setCname(rs.getString("cname"));
				dto.setCcode(rs.getInt("ccode"));
				dto.setBname(rs.getString("bname"));
				dto.setBtype(rs.getInt("btype"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return list;
	}

}
