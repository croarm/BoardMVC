package com.samsungsds.board.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlFormat {
	public static String eucEncoding(String tmp) {//¾ÈÈ¿ÀÎ >>> %BE%C8%C8%BF%C0%CE
		String euc = "";
			try {
				if(tmp != null)
					euc = URLEncoder.encode(tmp, "EUC-KR");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return euc;
	}
}
