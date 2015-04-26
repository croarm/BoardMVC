package com.samsungsds.board.model;

public class BbsDto extends BoardDto {
	private int bseq;
	private int seq;
	private String orign_file;
	private String save_file;
	private long filesize;

	public int getBseq() {
		return bseq;
	}

	public void setBseq(int bseq) {
		this.bseq = bseq;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getOrign_file() {
		return orign_file;
	}

	public void setOrign_file(String orign_file) {
		this.orign_file = orign_file;
	}

	public String getSave_file() {
		return save_file;
	}

	public void setSave_file(String save_file) {
		this.save_file = save_file;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
}
