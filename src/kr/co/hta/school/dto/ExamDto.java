package kr.co.hta.school.dto;

import java.util.Date;

public class ExamDto {
	private int no; // 시험성적번호
	private String type; // 시험종류
	private int score; // 시험점수
	private Date createDate; // 시험점수 등록일
	private int registrationNo; // 수강신청등록번호
	private int lecNo; // 강의번호
	private int lecName; // 강의이름
	private String profCode; // 강의개설 교수코드
	private String profName; // 강의개설 교수이름
	private String deptCode; // 강의개설 학과코드
	private String deptName; // 강의개설 학과이름
	private int studNo; // 학생번호
	private String studName; // 학생이름
	private int studGrade; // 학생의 학년
	
	public ExamDto() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public int getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(int registrationNo) {
		this.registrationNo = registrationNo;
	}

	public int getLecNo() {
		return lecNo;
	}

	public void setLecNo(int lecNo) {
		this.lecNo = lecNo;
	}

	public int getLecName() {
		return lecName;
	}

	public void setLecName(int lecName) {
		this.lecName = lecName;
	}

	public String getProfCode() {
		return profCode;
	}

	public void setProfCode(String profCode) {
		this.profCode = profCode;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getStudNo() {
		return studNo;
	}

	public void setStudNo(int studNo) {
		this.studNo = studNo;
	}

	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public int getStudGrade() {
		return studGrade;
	}

	public void setStudGrade(int studGrade) {
		this.studGrade = studGrade;
	}
	
	
}
