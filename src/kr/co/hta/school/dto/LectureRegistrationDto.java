package kr.co.hta.school.dto;

import java.util.Date;

public class LectureRegistrationDto {
	private int no; // 수강신청등록번호
	private int lecNo; // 강의번호
	private String lecName; // 강의이름
	private int lecYear; // 강의개설년도
	private int lecTerm; // 개설학기
	private int lecType; // 강의종류
	private int lecGrade; // 수강대상학년
	private int lecQuota; // 모집정원
	private int lecRegCnt; // 신청자 수
	private String lecClosed; // 폐강여부
	private String lecCompleted; // 모집완료여부
	private String profCode; // 강의개설 교수코드
	private String profName; // 강의개설 교수이름
	private String deptCode; // 강의개설 학과코드
	private String deptName; // 강의개설 학과이름
	private int studNo; // 수강신청 학생번호
	private String studName; // 수강신청 학생이름
	private int studGrade; // 수강신청 학생학년
	private Date createDate; // 수강신청정보 등록일자
	
	public LectureRegistrationDto() {}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getLecNo() {
		return lecNo;
	}
	public void setLecNo(int lecNo) {
		this.lecNo = lecNo;
	}
	public String getLecName() {
		return lecName;
	}
	public void setLecName(String lecName) {
		this.lecName = lecName;
	}
	public int getLecYear() {
		return lecYear;
	}
	public void setLecYear(int lecYear) {
		this.lecYear = lecYear;
	}
	public int getLecTerm() {
		return lecTerm;
	}
	public void setLecTerm(int lecTerm) {
		this.lecTerm = lecTerm;
	}
	public int getLecType() {
		return lecType;
	}
	public void setLecType(int lecType) {
		this.lecType = lecType;
	}
	public int getLecGrade() {
		return lecGrade;
	}
	public void setLecGrade(int lecGrade) {
		this.lecGrade = lecGrade;
	}
	public int getLecQuota() {
		return lecQuota;
	}
	public void setLecQuota(int lecQuota) {
		this.lecQuota = lecQuota;
	}
	public int getLecRegCnt() {
		return lecRegCnt;
	}
	public void setLecRegCnt(int lecRegCnt) {
		this.lecRegCnt = lecRegCnt;
	}
	public String getLecClosed() {
		return lecClosed;
	}
	public void setLecClosed(String lecClosed) {
		this.lecClosed = lecClosed;
	}
	public String getLecCompleted() {
		return lecCompleted;
	}
	public void setLecCompleted(String lecCompleted) {
		this.lecCompleted = lecCompleted;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
