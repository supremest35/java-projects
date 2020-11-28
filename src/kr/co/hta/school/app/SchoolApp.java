package kr.co.hta.school.app;

import kr.co.hta.school.controller.ProfessorController;
import kr.co.hta.school.controller.StudentController;
import kr.co.hta.school.util.Keyboard;

public class SchoolApp {

	public static void main(String[] args) {
		StudentController studentController = new StudentController();
		ProfessorController professorController = new ProfessorController();

		while (true) {
			System.out.println("-----------------------------------------------");
			System.out.println("1.학생기능  2.교수기능  0.종료");
			System.out.println("-----------------------------------------------");

			System.out.print("메뉴 선택 > ");
			int menuNo = Keyboard.nextInt();

			if (menuNo == 1) {
				studentController.displayMenu();
			} else if (menuNo == 2) {
				professorController.displayMenu();
			} else if (menuNo == 0) {
				System.out.println("### 수강신청프로그램 종료");
				break;
			}
		}
	}
}
