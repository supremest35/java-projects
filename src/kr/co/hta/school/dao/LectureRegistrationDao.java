package kr.co.hta.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.hta.school.dto.LectureRegistrationDto;
import kr.co.hta.school.util.ConnectionUtil;
import kr.co.hta.school.vo.LectureRegistration;

public class LectureRegistrationDao {
	
	private static final String GET_REGISTRATION_BY_STUDENT_NO_SQL = "select a.registration_no, a.lecture_no, b.lecture_name, b.lecture_year, b.lecture_term, b.lecture_type, b.lecture_grade,"
			+ "b.lecture_quota, b.lecture_reg_cnt, b.lecture_closed, b.lecture_completed, b.prof_code, c.prof_name, b.dept_code, d.dept_name, e.student_no, e.student_name, e.student_grade, a.registration_create_date "
			+ "from school_lectures_registrations a, school_lectures b, school_professors c, school_departments d, school_students e "
			+ "where a.lecture_no = b.lecture_no "
			+ "and b.prof_code = c.prof_code "
			+ "and b.dept_code = d.dept_code "
			+ "and a.student_no = e.student_no "
			+ "and a.student_no = ?";
	
	private static final String INSERT_LECTURE_REGISTRATION_SQL = "insert into school_lectures_registrations(registration_no, student_no, lecture_no)"
			+ "values(school_registration_seq.nextval, ?, ?)";
	
	/*
	 * 수강신청번호 강의번호(lectureRegistration) 강의이름 강의개설년도 개설학기 강의종류 수강대상학년
	 * 모집정원 신청자 수 폐강여부 모집완료여부 강의개설교수코드(lecture), 강의개설교수이름(professor), 강의개설학과코드(lecture), 
	 * 강의개설학과이름(department) 수강신청학생이름, 수강신청학생번호 수강신청학생이름, 수강신청학생학년 (student) 수강신청정보등록일(lectureRegistration)
	 * 
	 * 
	 */
	
	
	
	// 수강신청정보를 전달받아서 데이터베이스에 반영한다.
	public void insertLectureRegistration(LectureRegistration lectureRegistration) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(INSERT_LECTURE_REGISTRATION_SQL);
		pstmt.setInt(1, lectureRegistration.getStudentNo());
		pstmt.setInt(2, lectureRegistration.getLectureNo());
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}

	// 학번을 전달받아서 수강신청정보를 데이터베이스에서 조회한다.
	public List<LectureRegistrationDto> getLectureRegistrationsByStudentNo(int studentNo) throws SQLException {
		List<LectureRegistrationDto> lectureRegistrationDtos = new ArrayList<LectureRegistrationDto>();
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(GET_REGISTRATION_BY_STUDENT_NO_SQL);
		pstmt.setInt(1, studentNo);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			LectureRegistrationDto lectureRegistrationDto = new LectureRegistrationDto();
			lectureRegistrationDto.setNo(rs.getInt("registration_no"));
			lectureRegistrationDto.setLecNo(rs.getInt("lecture_no"));
			lectureRegistrationDto.setLecName(rs.getString("lecture_name"));
			lectureRegistrationDto.setLecYear(rs.getInt("lecture_year"));
			lectureRegistrationDto.setLecTerm(rs.getInt("lecture_term"));
			lectureRegistrationDto.setLecType(rs.getInt("lecture_type"));
			lectureRegistrationDto.setLecGrade(rs.getInt("lecture_grade"));
			lectureRegistrationDto.setLecQuota(rs.getInt("lecture_quota"));
			lectureRegistrationDto.setLecRegCnt(rs.getInt("lecture_reg_cnt"));
			lectureRegistrationDto.setLecClosed(rs.getString("lecture_closed"));
			lectureRegistrationDto.setLecCompleted(rs.getString("lecture_completed"));
			lectureRegistrationDto.setProfCode(rs.getString("prof_code"));
			lectureRegistrationDto.setProfName(rs.getString("prof_name"));
			lectureRegistrationDto.setDeptCode(rs.getString("dept_code"));
			lectureRegistrationDto.setDeptName(rs.getString("dept_name"));
			lectureRegistrationDto.setStudNo(rs.getInt("student_no"));
			lectureRegistrationDto.setStudName(rs.getString("student_name"));
			lectureRegistrationDto.setStudGrade(rs.getInt("student_grade"));
			lectureRegistrationDto.setCreateDate(rs.getDate("registration_create_date"));
			lectureRegistrationDtos.add(lectureRegistrationDto);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		return lectureRegistrationDtos;
	}
}
