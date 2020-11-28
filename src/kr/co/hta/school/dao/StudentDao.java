package kr.co.hta.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.co.hta.school.util.ConnectionUtil;
import kr.co.hta.school.vo.Student;

public class StudentDao {
	
	private static final String GET_STUDENT_BY_NO_SQL = "select * from school_students where student_no = ?";
	
	// 학번을 전달받아서 학생정보를 반환한다.
	public Student getStudentByNo(int studentNo) throws SQLException {
		Student student = null;
		
		Connection con = ConnectionUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement(GET_STUDENT_BY_NO_SQL);
		pstmt.setInt(1, studentNo);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			student = new Student();
			student.setNo(rs.getInt("student_no"));
			student.setName(rs.getString("student_name"));
			student.setEmail(rs.getString("student_email"));
			student.setGrade(rs.getInt("student_grade"));
			student.setDeptCode(rs.getString("dept_code"));
			student.setCreateDate(rs.getDate("student_create_date"));
		}
		
		rs.close();
		pstmt.close();
		con.close();
		return student;
	}
}
