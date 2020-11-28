package kr.co.hta.school.controller;

import java.sql.SQLException;
import java.util.List;

import kr.co.hta.school.dto.LectureRegistrationDto;
import kr.co.hta.school.exception.SchoolException;
import kr.co.hta.school.service.StudentService;
import kr.co.hta.school.util.Keyboard;
import kr.co.hta.school.vo.Lecture;

public class StudentController {
	
	private StudentService studentService = new StudentService();
	
	public void displayMenu() {
		while (true) {
			try {
				System.out.println("[학생전용기능]");
				System.out.println("---------------------------------------------------------------");
				System.out.println("1.개설강의 조회  2.수강신청  3.수강신청내역조회  4.성적조회  0.종료");
				System.out.println("---------------------------------------------------------------");

				System.out.print("메뉴 선택 > ");
				int menuNo = Keyboard.nextInt();

				if (menuNo == 1) {
					viewAvailableLectures();
				} else if (menuNo == 2) {
					registerLectureRegistration();
				} else if (menuNo == 3) {
					viewMyLectureRegistrations();
				} else if (menuNo == 4) {
					viewMyExamScores();
				} else if (menuNo == 0) {
					System.out.println("### 학생전용기능 종료");
				}
			} catch (SchoolException e) {
				System.out.println("[오류:수강신청 업무로직 오류] " + e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("[오류:데이터베이스 엑세스 오류] " + e.getMessage());
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("[오류:알 수 업슨 오류] " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	// 학번을 입력받아서 그 학생이 소속된 학과에서 개설한 수강신청 가능한 개설강의 목록을 화면에 출력한다.
	private void viewAvailableLectures() throws SQLException {
		// 1. 학번을 입력받는다.
		// 2. StudentService의 getAvailableLectures메소드를 호출해서 수강신청가능한 강의목록을 조회한다.
		// 3. 조회된 강의목록을 화면에 출력한다.
		System.out.println("[개설강의 조회]");
		
		System.out.println("### 학번을 입력하세요!");
		System.out.print("학번 >> ");
		int studentNo = Keyboard.nextInt();
		List<Lecture> lectureList = studentService.getAvailableLectures(studentNo);		
		System.out.println();
		
		for (Lecture lecture : lectureList) {
			System.out.println("---------------------------------------------------------------");
			System.out.println("강의번호: " + lecture.getNo());
			System.out.println("강의명: " + lecture.getName());
			System.out.println("개설년도: " + lecture.getYear());
			System.out.println("개설학기: " + lecture.getTerm());
			System.out.println("강의종류: " + lecture.getType());
			System.out.println("수강대상학년: " + lecture.getGrade());
			System.out.println("신청정원: " + lecture.getQuota());
			System.out.println("신청자수: " + lecture.getRegCnt());
			System.out.println("폐강여부: " + lecture.getClosed());
			System.out.println("모집마감여부: " + lecture.getCompleted());
			System.out.println("강의개설 교수코드: " + lecture.getProfCode());
			System.out.println("개설학과 코드: " + lecture.getDeptCode());
			System.out.println("강의등록일: " + lecture.getCreateDate());
			System.out.println("---------------------------------------------------------------");
			System.out.println();
		}
		System.out.println();	
	}

	// 개설강의번호와 학번을 입력받아서 수강신청테이블에 수강신청정보를 저장한다.
	private void registerLectureRegistration() throws SQLException {
		// 1. 개설강의번호와 학번을 입력받는다.
		// 2. StudentService의 registerLectureRegistration메소드를 호출해서 수강신청업무를 수행한다.
		System.out.println("[수강신청]");
		
		System.out.println("### 학번과 개설강의번호를 입력하세요!");
		System.out.print("학번 >> ");
		int studentNo = Keyboard.nextInt();
		System.out.print("개설강의번호 >> ");
		int lectureNo = Keyboard.nextInt();
		
		studentService.registerLectureRegistration(lectureNo, studentNo);
		System.out.println("### 수강신청이 완료되었습니다.");
		
	}

	// 학번을 입력받아서 그 학생이 수강신청한 강의목록을 화면에 출력한다.
	private void viewMyLectureRegistrations() throws SQLException {
		// 1. 학번을 입력받는다.
		// 2. StudentService의 getMyLectureRegistrations메소드를 호출해서 수강신청한 강의목록을 조회한다.
		// 3. 조회된 강의목록을 화면에 출력한다.
		System.out.println("[수강신청내역 조회]");
		
		System.out.println("### 학번을 입력하세요!");
		System.out.print("학번 >> ");
		int studentNo = Keyboard.nextInt();
		
		List<LectureRegistrationDto> lectureRegistrationDtos = studentService.getMyLectureRegistrations(studentNo);
		
		System.out.println("---------------------------------------------------------------");
		for (LectureRegistrationDto lectureRegistrationDto : lectureRegistrationDtos) {
			System.out.println("수강신청등록번호: " + lectureRegistrationDto.getNo());
			System.out.println("강의번호: " + lectureRegistrationDto.getLecNo());
			System.out.println("강의이름: " + lectureRegistrationDto.getLecName());
			System.out.println("강의개설연도: " + lectureRegistrationDto.getLecYear());
			System.out.println("개설학기: " + lectureRegistrationDto.getLecTerm());
			System.out.println("강의종류: " + lectureRegistrationDto.getLecType());
			System.out.println("수강대상학년: " + lectureRegistrationDto.getLecGrade());
			System.out.println("모집정원: " + lectureRegistrationDto.getLecQuota());
			System.out.println("신청자수: " + lectureRegistrationDto.getLecRegCnt());
			System.out.println("폐강여부: " + lectureRegistrationDto.getLecClosed());
			System.out.println("모집완료여부: " + lectureRegistrationDto.getLecCompleted());
			System.out.println("강의개설 교수코드: " + lectureRegistrationDto.getProfCode());
			System.out.println("강의개설 교수이름: " + lectureRegistrationDto.getProfName());
			System.out.println("강의개설 학과코드: " + lectureRegistrationDto.getDeptCode());
			System.out.println("강의개설 학과이름: " + lectureRegistrationDto.getDeptName());
			System.out.println("수강신청 학생번호: " + lectureRegistrationDto.getStudNo());
			System.out.println("수강신청 학생이름: " + lectureRegistrationDto.getStudName());
			System.out.println("수강신청 학생학년: " + lectureRegistrationDto.getStudGrade());
			System.out.println("수강신청정보 등록일: " + lectureRegistrationDto.getCreateDate());
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println();
	}

	// 학번을 입력받아서 그 학생의 시험성적정보를 화면에 출력한다.
	private void viewMyExamScores() throws SQLException {
		// 1. 학번을 입력받는다.
		// 2. StudentService의 getMyExamScores메소드를 호출해서 시험성적정보를 조회한다.
		// 3. 조회된 시험성적정보를 화면에 출력한다.
	}
}
