package com.samsungsds.board.model;

public class AlbumDto extends BoardDto {
	private int aseq;
	private int seq;
	private String orign_picture;
	private String save_picture;

	public int getAseq() {
		return aseq;
	}

	public void setAseq(int aseq) {
		this.aseq = aseq;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getOrign_picture() {
		return orign_picture;
	}

	public void setOrign_picture(String orign_picture) {
		this.orign_picture = orign_picture;
	}

	public String getSave_picture() {
		return save_picture;
	}

	public void setSave_picture(String save_picture) {
		this.save_picture = save_picture;
	}

}
