package com.samsungsds.board.model;

import java.sql.*;
import java.util.*;

import com.samsungsds.board.util.db.DBClose;
import com.samsungsds.board.util.db.DBConnection;

public class ReboardDao {

	public int getNextSeq() {
		int seq = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnection();
			String sql = "select board_seq.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			seq = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return seq;
	}

	public int wrtieNewArticle(ReboardDto reboardDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.makeConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("insert all \n");
			sb.append("		  into board (seq, name, id, email, subject, content, hit, logtime, bcode) \n");
			sb.append("	 	  values (?, ?, ?, ?, ?, ?, 0, sysdate, ?) \n");
			sb.append("		  into reboard (rseq, seq, ref, lev, step, pseq, reply) \n");
			sb.append("		  values (reboard_rseq.nextval, ?, ?, 0, 0, 0, 0) \n");
			sb.append("select * from dual");

			pstmt = conn.prepareStatement(sb.toString());
			int idx = 0;
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setString(++idx, reboardDto.getName());
			pstmt.setString(++idx, reboardDto.getId());
			pstmt.setString(++idx, reboardDto.getEmail());
			pstmt.setString(++idx, reboardDto.getSubject());
			pstmt.setString(++idx, reboardDto.getContent());
			pstmt.setInt(++idx, reboardDto.getBcode());
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setInt(++idx, reboardDto.getRef());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}

	public ReboardDto getArticle(int seq) {
		ReboardDto reboardDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("select b.seq, b.name, b.id, b.email, \n");
			sb.append("		  b.subject, b.content, b.hit, \n");
			sb.append("		  b.logtime, b.bcode, r.rseq, \n");
			sb.append("		  r.ref, r.lev, r.step, r.pseq, r.reply \n");
			sb.append("		  from board b, reboard r \n");
			sb.append("where b.seq=r.seq \n");
			sb.append("and b.seq=?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				reboardDto = new ReboardDto();
				reboardDto.setSeq(rs.getInt("seq"));
				reboardDto.setName(rs.getString("name"));
				reboardDto.setId(rs.getString("id"));
				reboardDto.setEmail(rs.getString("email"));
				reboardDto.setSubject(rs.getString("subject"));
				reboardDto.setContent(rs.getString("content"));
				reboardDto.setHit(rs.getInt("hit"));
				reboardDto.setLogtime(rs.getString("logtime"));
				reboardDto.setBcode(rs.getInt("bcode"));
				reboardDto.setRseq(rs.getInt("rseq"));
				reboardDto.setRef(rs.getInt("ref"));
				reboardDto.setLev(rs.getInt("lev"));
				reboardDto.setStep(rs.getInt("step"));
				reboardDto.setPseq(rs.getInt("pseq"));
				reboardDto.setReply(rs.getInt("reply"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return reboardDto;
	}

	public List<ReboardDto> getArticleList(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<ReboardDto> list = new ArrayList<ReboardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.makeConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("select b.* \n");
			sb.append("from ( \n");
			sb.append("    select rownum rn, a.* \n");
			sb.append("    from ( \n");
			sb.append("        select b.seq, b.name, b.id, b.email, \n");
			sb.append("               b.subject, b.content, b.hit, \n");
			sb.append("               decode(trunc(b.logtime, 'dd'), \n");
			sb.append("                      trunc(sysdate, 'dd'), to_char(logtime, 'hh24:mi:ss'), \n");
			sb.append("                      to_char(logtime, 'yy.mm.dd')) as logtime,  \n");
			sb.append("               b.bcode, r.rseq, \n");
			sb.append("               r.ref, r.lev, r.step, r.pseq, r.reply \n");
			sb.append("        from board b, reboard r \n");
			sb.append("        where b.seq=r.seq \n");
			sb.append("        and b.bcode=? \n");
			
			String word = map.get("word");
			if(word !=null&&word.length()!=0){
				String key = map.get("key");
				if(key.equals("subject")){//제목(like 검색)
				sb.append("        and b.subject like '%'||?||'%' \n");
				}else{//이름 번호(equal검색)
				sb.append("        and b."+key+"=? \n");
				}
			}
			
			
			
			sb.append("        order by ref desc, step \n");
			sb.append("        ) a \n");
			sb.append("    where rownum <= ? \n");
			sb.append("    ) b \n");
			sb.append("where b.rn > ? \n");
			pstmt = conn.prepareStatement(sb.toString());
			int idx=0;
			pstmt.setString(++idx, map.get("bcode"));
			if(word!=null&&word.length()!=0){
				pstmt.setString(++idx, word);
			}
			pstmt.setString(++idx, map.get("end"));
			pstmt.setString(++idx, map.get("start"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReboardDto reboardDto = new ReboardDto();
				reboardDto.setSeq(rs.getInt("seq"));
				reboardDto.setName(rs.getString("name"));
				reboardDto.setId(rs.getString("id"));
				reboardDto.setEmail(rs.getString("email"));
				reboardDto.setSubject(rs.getString("subject"));
				reboardDto.setContent(rs.getString("content"));
				reboardDto.setHit(rs.getInt("hit"));
				reboardDto.setLogtime(rs.getString("logtime"));
				reboardDto.setBcode(rs.getInt("bcode"));
				reboardDto.setRseq(rs.getInt("rseq"));
				reboardDto.setRef(rs.getInt("ref"));
				reboardDto.setLev(rs.getInt("lev"));
				reboardDto.setStep(rs.getInt("step"));
				reboardDto.setPseq(rs.getInt("pseq"));
				reboardDto.setReply(rs.getInt("reply"));
				
				list.add(reboardDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}

		return list;
	}

	public void updateHit(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.makeConnection();
			String sql = "update board set hit=hit+1 where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
	}

	public int getArticleCount(Map<String, String> map, String kind) {
		int articleCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sb = new StringBuffer("");
			sb.append("select count(seq) \n");
			sb.append("from board \n");
			sb.append("where bcode=? \n");
			String word = null;
			if(kind.equals("new"))
				sb.append("and trunc(logtime,'dd')=trunc(sysdate,'dd')");
			else{
			word = map.get("word");
			if(word !=null&&word.length()!=0){
				String key = map.get("key");
				if(key.equals("subject")){//제목(like 검색)
				sb.append("        and subject like '%'||?||'%' \n");
				}else{//이름 번호(equal검색)
				sb.append("        and "+key+"=? \n");
				}
			}
			}
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1,map.get("bcode"));
			if(word !=null&&word.length()!=0)
				pstmt.setString(2, word);
			rs = pstmt.executeQuery();
			rs.next();
			articleCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return articleCount;
	}

	public int modifyArticle(ReboardDto reboardDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.makeConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("update board set \n");
			sb.append("subject ='subject', \n");
			sb.append("content='content' \n");
			sb.append("where id=id and b.seq=?\n");
						
			
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 0;
			pstmt.setString(++idx, reboardDto.getId());
			pstmt.setString(++idx, reboardDto.getSubject());
			pstmt.setString(++idx, reboardDto.getContent());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}

	public int writeReplyArticle(ReboardDto reboardDto) {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.makeConnection();
			conn.setAutoCommit(false);
			
			String update_step = "update reboard set step=step+1 \n";
			update_step += "where ref=? and step>?";
			pstmt = conn.prepareStatement(update_step);
			pstmt.setInt(1, reboardDto.getRef());
			pstmt.setInt(2, reboardDto.getStep());
			pstmt.executeUpdate();
						
			StringBuffer sb = new StringBuffer();
			sb.append("insert all \n");
			sb.append("		  into board (seq, name, id, email, subject, content, hit, logtime, bcode) \n");
			sb.append("	 	  values (?, ?, ?, ?, ?, ?, 0, sysdate, ?) \n");
			sb.append("		  into reboard (rseq, seq, ref, lev, step, pseq, reply) \n");
			sb.append("		  values (reboard_rseq.nextval, ?, ?, ?, ?, ?, 0) \n");
			sb.append("select * from dual");
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 0;
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setString(++idx, reboardDto.getName());
			pstmt.setString(++idx, reboardDto.getId());
			pstmt.setString(++idx, reboardDto.getEmail());
			pstmt.setString(++idx, reboardDto.getSubject());
			pstmt.setString(++idx, reboardDto.getContent());
			pstmt.setInt(++idx, reboardDto.getBcode());
			pstmt.setInt(++idx, reboardDto.getSeq());
			pstmt.setInt(++idx, reboardDto.getRef());
			pstmt.setInt(++idx, reboardDto.getLev()+1);
			pstmt.setInt(++idx, reboardDto.getStep()+1);
			pstmt.setInt(++idx, reboardDto.getPseq());
			pstmt.executeUpdate();
			
			String update_reply = "update reboard set reply=reply+1 \n";
			update_reply += "where seq=?";
			pstmt = conn.prepareStatement(update_reply);
			pstmt.setInt(1, reboardDto.getPseq());
			pstmt.executeUpdate();
			
			conn.commit();
			cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		
		return cnt;
	}


}





















