package kr.co.hta.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.hta.school.util.ConnectionUtil;
import kr.co.hta.school.vo.Lecture;

public class LectureDao {
	
	private static final String GET_LECTURES_BY_DEPT_CODE_SQL = "select * from school_lectures where dept_code = ?";
	private static final String GET_LECTURE_BY_NO_SQL = "select * from school_lectures where lecture_no = ?";
	private static final String UPDATE_LECTURE_SQL = "update school_lectures set lecture_quota = ?, lecture_reg_cnt = ?, lecture_closed = ?, lecture_completed = ? "
			+ "where lecture_no = ?";
	
	
	// 학과코드를 전달받아서 개설강의목록을 데이터베이스에서 조회한다.
	public List<Lecture> getLecturesByDeptCode(String deptCode) throws SQLException {
		List<Lecture> lectures = new ArrayList<Lecture>();
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(GET_LECTURES_BY_DEPT_CODE_SQL);
		pstmt.setString(1, deptCode);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Lecture lecture = new Lecture();
			lecture.setNo(rs.getInt("lecture_no"));
			lecture.setName(rs.getString("lecture_name"));
			lecture.setYear(rs.getInt("lecture_year"));
			lecture.setTerm(rs.getInt("lecture_term"));
			lecture.setType(rs.getInt("lecture_type"));
			lecture.setGrade(rs.getInt("lecture_grade"));
			lecture.setQuota(rs.getInt("lecture_quota"));
			lecture.setRegCnt(rs.getInt("lecture_reg_cnt"));
			lecture.setClosed(rs.getString("lecture_closed"));
			lecture.setCompleted(rs.getString("lecture_completed"));
			lecture.setProfCode(rs.getString("prof_code"));
			lecture.setDeptCode(rs.getString("dept_code"));
			lecture.setCreateDate(rs.getDate("lecture_create_date"));
			lectures.add(lecture);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		return lectures;
	}

	// 개설강의번호를 전달받아서 개설강의정보를 데이터베이스에서 조회한다.
	public Lecture getLectureByNo(int lectureNo) throws SQLException {
		Lecture lecture = null;
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(GET_LECTURE_BY_NO_SQL);
		pstmt.setInt(1, lectureNo);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			lecture = new Lecture();
			lecture.setNo(rs.getInt("lecture_no"));
			lecture.setName(rs.getString("lecture_name"));
			lecture.setYear(rs.getInt("lecture_year"));
			lecture.setTerm(rs.getInt("lecture_term"));
			lecture.setType(rs.getInt("lecture_type"));
			lecture.setGrade(rs.getInt("lecture_grade"));
			lecture.setQuota(rs.getInt("lecture_quota"));
			lecture.setRegCnt(rs.getInt("lecture_reg_cnt"));
			lecture.setClosed(rs.getString("lecture_closed"));
			lecture.setCompleted(rs.getString("lecture_completed"));
			lecture.setProfCode(rs.getString("prof_code"));
			lecture.setDeptCode(rs.getString("dept_code"));
			lecture.setCreateDate(rs.getDate("lecture_create_date"));
		}
		
		rs.close();
		pstmt.close();
		con.close();
		return lecture;
	}

	// 변경된 개설강의정보를 전달받아서 데이터베이스에 반영한다.
	public void updateLecture(Lecture lecture) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(UPDATE_LECTURE_SQL);
		pstmt.setInt(1, lecture.getQuota());
		pstmt.setInt(2, lecture.getRegCnt());
		pstmt.setString(3, lecture.getClosed());
		pstmt.setString(4, lecture.getCompleted());
		pstmt.setInt(5, lecture.getNo());
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
	}
}
