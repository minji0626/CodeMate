package kr.mate.vo;

import java.sql.Date;

public class MateVO {
	// 메이트 하드 스킬
	private int mh_num;
	private int mem_num;
	private int hs_code;
	private int mh_pro;

	// 메이트 소프트 스킬
	private int ms_num;
	private int ss_code;

	// 메이트 리뷰
	private int mr_num;				// 리뷰 번호
	private int mr_writer;			// 리뷰 작성자
	private int mr_receiver;		// 리뷰 받는 사람
	private Date mr_regDate;		// 리뷰 작성일
	private String mr_content;		// 리뷰 내용
	public int getMh_num() {
		return mh_num;
	}
	public void setMh_num(int mh_num) {
		this.mh_num = mh_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getHs_code() {
		return hs_code;
	}
	public void setHs_code(int hs_code) {
		this.hs_code = hs_code;
	}
	public int getMh_pro() {
		return mh_pro;
	}
	public void setMh_pro(int mh_pro) {
		this.mh_pro = mh_pro;
	}
	public int getMs_num() {
		return ms_num;
	}
	public void setMs_num(int ms_num) {
		this.ms_num = ms_num;
	}
	public int getSs_code() {
		return ss_code;
	}
	public void setSs_code(int ss_code) {
		this.ss_code = ss_code;
	}
	public int getMr_num() {
		return mr_num;
	}
	public void setMr_num(int mr_num) {
		this.mr_num = mr_num;
	}
	public int getMr_writer() {
		return mr_writer;
	}
	public void setMr_writer(int mr_writer) {
		this.mr_writer = mr_writer;
	}
	public int getMr_receiver() {
		return mr_receiver;
	}
	public void setMr_receiver(int mr_receiver) {
		this.mr_receiver = mr_receiver;
	}
	public Date getMr_regDate() {
		return mr_regDate;
	}
	public void setMr_regDate(Date mr_regDate) {
		this.mr_regDate = mr_regDate;
	}
	public String getMr_content() {
		return mr_content;
	}
	public void setMr_content(String mr_content) {
		this.mr_content = mr_content;
	}

	

}
