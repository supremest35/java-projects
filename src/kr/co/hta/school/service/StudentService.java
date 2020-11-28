package kr.co.hta.school.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.hta.school.dao.ExamDao;
import kr.co.hta.school.dao.LectureDao;
import kr.co.hta.school.dao.LectureRegistrationDao;
import kr.co.hta.school.dao.StudentDao;
import kr.co.hta.school.dto.ExamDto;
import kr.co.hta.school.dto.LectureRegistrationDto;
import kr.co.hta.school.exception.AlreadyRegistrationLectureException;
import kr.co.hta.school.exception.LectureFailureException;
import kr.co.hta.school.exception.LectureNotFoundException;
import kr.co.hta.school.exception.StudentNotFoundException;
import kr.co.hta.school.vo.Lecture;
import kr.co.hta.school.vo.LectureRegistration;
import kr.co.hta.school.vo.Student;

public class StudentService {
	private StudentDao studentDao = new StudentDao();
	private LectureDao lectureDao = new LectureDao();
	private LectureRegistrationDao lectureRegistrationDao = new LectureRegistrationDao();
	private ExamDao examDao = new ExamDao();
	
	private static final String LECTURE_CLOSED_YES = "Y";
	private static final String LECTURE_CLOSED_NO = "N";
	private static final String LECTURE_COMPLETED_YES = "Y";
	private static final String LECTURE_COMPLETED_NO = "N";
	

	// 학번을 전달받아서 이 학생이 소속된 학과에서 개설한 개설강의목록을 반환한다.
	public List<Lecture> getAvailableLectures(int studentNo) throws SQLException {
		// 1. 학생테이블(SCHOOL_STUDENTS)에서 학번으로 학생정보를 조회한다.
		// 2. 학생정보가 존재하지 않으면 StudentNotFoundException을 발생시킨다.
		// 3. 학생정보에서 학과코드를 획득한다.
		// 4. 개설강의정보테이블(SCHOOL_LECTURES)에서 학과코드로 개설강의를 조회한다.
		// 5. 조회된 개설강의 목록을 반환한다.
		Student student = studentDao.getStudentByNo(studentNo);
		
		if (student == null) {
			throw new StudentNotFoundException("["+studentNo+"] 학번의 학생이 존재하지 않습니다.");
		}
		
		return lectureDao.getLecturesByDeptCode(student.getDeptCode());
	}

	// 강의번호와 학번을 전달받아서 수강신청등록 테이블에 수강신청정보를 저장한다.
	public void registerLectureRegistration(int lectureNo, int studentNo) throws SQLException {
		// 1. 학생테이블(SCHOOL_STUDENTS)에서 학번으로 학생정보를 조회한다.
		// 2. 학생정보가 존재하지 않으면 StudentNotFoundException을 발생시킨다.
		// 3. 수강신청등록 테이블(SCHOOL_LECTURES_REGISTRATIONS)에서 학번과 학과코드로 수강신청정보를 조회한다.
		// 4. 수강신청정보가 존재하면 AlreadyRegistrationLectureException을 발생시킨다.
		// 5. 개설강의정보테이블(SCHOOL_LECTURES)에서 개설강의번호로 개설강의정보를 조회한다.
		// 6. 개설강의정보가 존재하지 않으면 LectureNotFoundException을 발생시킨다.
		// 7. 개설강의정보에서 모집마감여부를 조회한다.
		// 8. 모집마감여부가 'Y'인 경우 LectureFailureException을 발생시킨다.
		// 9. 수강신청등록 테이블(SCHOOL_LECTURES_REGISTRATIONS)에 수강신청등록번호, 학생번호, 강의번호를 저장한다.
		// 10. 개설강의정보테이블(SCHOOL_LECTURES)의 신청자수를 1증가시킨다.
		Student student = studentDao.getStudentByNo(studentNo);
		
		if (student == null) {
			throw new StudentNotFoundException("["+studentNo+"] 학번의 학생이 존재하지 않습니다.");
		}
		
		List<LectureRegistrationDto> lectureRegistrationDtos = new ArrayList<LectureRegistrationDto>();
		lectureRegistrationDtos = lectureRegistrationDao.getLectureRegistrationsByStudentNo(studentNo);
		
		for (LectureRegistrationDto lectureRegistrationDto : lectureRegistrationDtos) {
			if (lectureRegistrationDto.getLecNo() == lectureNo) {
				throw new AlreadyRegistrationLectureException("["+studentNo+"]번의 학생은 이미 ["+lectureNo+"] 강의를 수강신청 완료했습니다.");
			}
		}
		
		Lecture lecture = lectureDao.getLectureByNo(lectureNo);
		if (lecture == null) {
			throw new LectureNotFoundException("["+lectureNo+"] 강의는 존재하지 않습니다.");
		}
		
		if (LECTURE_COMPLETED_YES.equals(lecture.getCompleted())) {
			throw new LectureFailureException("["+lectureNo+"] 강의는 모집마감되었습니다.");
		}
		
		LectureRegistration lectureRegistration = new LectureRegistration();
		lectureRegistration.setStudentNo(studentNo);
		lectureRegistration.setLectureNo(lectureNo);
		lectureRegistrationDao.insertLectureRegistration(lectureRegistration);
		
		lecture.setRegCnt(lecture.getRegCnt()+1);
		lectureDao.updateLecture(lecture);
	}

	// 학번을 전달받아서 수강신청테이블에서 수강신청한 강의목록을 반환한다.
	public List<LectureRegistrationDto> getMyLectureRegistrations(int studentNo) throws SQLException {
		// 1. 학생테이블(SCHOOL_STUDENTS)에서 학번으로 학생정보를 조회한다.
		// 2. 학생정보가 존재하지 않으면 StudentNotFoundException을 발생시킨다.
		// 4. 수강신청등록테이블(SCHOOL_LECTURES_REGISTRATIONS), 개설강의정보테이블(SCHOOL_LECTURES),
		// 교수정보테이블(SCHOOL_PROFESSORS)을
		// 조인하고 학번으로 학생이 수강신청등록한 개설강의정보를 조회한다.
		// 5. 조회된 개설강의목록정보를 반환한다.
		Student student = studentDao.getStudentByNo(studentNo);
		if (student == null) {
			throw new StudentNotFoundException("["+studentNo+"] 학번의 학생이 존재하지 않습니다.");
		}
		return lectureRegistrationDao.getLectureRegistrationsByStudentNo(studentNo);		
	}

	// 학번을 전달받아서 성적정보를 반환한다.
	public List<ExamDto> getMyExamScore(int studentNo) throws SQLException {
		// 1. 학생테이블(SCHOOL_STUDENTS)에서 학번으로 학생정보를 조회한다.
		// 2. 학생정보가 존재하지 않으면 StudentNotFoundException을 발생시킨다.
		// 3. 시험정보테이블(SCHOOL_EXAMS)와 개설강의정보테이블(SCHOOL_LECTURES)을 조인하고 학번으로 성적정보를 조회한다.
		// 4. 조회된 시험성적정보를 반환한다.
		return null;
	}
}
