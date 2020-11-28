package kr.co.hta.school.vo;

import java.util.Date;

public class Exam {

	private int no;
	private int registerationNo;
	private String type;
	private int score;
	private Date createDate;
	
	public Exam() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getRegisterationNo() {
		return registerationNo;
	}

	public void setRegisterationNo(int registerationNo) {
		this.registerationNo = registerationNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
