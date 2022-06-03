package toyproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MemberManager {

	private String memberId;
	private String name;
	private String phoneNum;
	int input = 0;
	MemberDAO dao = new MemberDAO();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public void MemberManager() {
	}

	public void readMenu() {

		try {
			while (true) {

				System.out.println("목록을 원하시면 1번을 입력하세요.");
				System.out.println("등록을 원하시면 2번을 입력하세요.");
				System.out.println("수정을 원하시면 3번을 입력하세요.");
				System.out.println("삭제를 원하시면 4번을 입력하세요.");
				System.out.println("종료를 원하시면 0번을 입력하세요.");

				input = Integer.parseInt(br.readLine());

				if (input == 1) {
					getMemberList();
				} else if (input == 2) {
					insertMember();
				} else if (input == 3) {
					updateMember();
				} else if (input == 4) {
					deleteMember();
				} else if (input == 0) {
					break;
				} else {
					System.out.println("유효하지 않은 번호입니다.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	public void getMemberList() {
		dao.getMemberList();
	}

	public void insertMember() {

		try {

			System.out.println("아이디를 입력하세요 (형식 M-00001) : " + "\r\n");
			memberId = br.readLine();

			if (dao.duplicateId(memberId)) {
				System.out.println(memberId + "는 중복된 ID입니다.");
				return;
			}

			System.out.print("이름을 입력하세요 : " + "\r\n");
			name = br.readLine();
			System.out.print("전화번호를 입력하세요 : " + "\r\n");
			phoneNum = br.readLine();

			dao.insertMember(memberId, name, phoneNum);

			System.out.println("---> 회원가입에 성공하셨습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateMember() {

		try {

			System.out.print("수정할 아이디를 입력하세요 (형식 M-00001) : ");
			memberId = br.readLine();
			System.out.print("수정할 전화번호를 입력하세요 : ");
			phoneNum = br.readLine();

			dao.updateMember(phoneNum, memberId);

			System.out.println("---> 회원수정에 성공하셨습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteMember() {

		try {

			System.out.print("삭제할 아이디를 입력하세요 (형식 M-00001) : ");
			memberId = br.readLine();

			dao.deleteMember(memberId);

			System.out.println("---> " + memberId + "회원 삭제에 성공하셨습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
