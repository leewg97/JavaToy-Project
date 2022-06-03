package toyproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	private final String MEMBER_LIST = "SELECT * FROM MEMBER";
	private final String MEMBER_INSERT = "INSERT INTO MEMBER VALUES(?,?,?)";
	private final String MEMBER_UPDATE = "UPDATE MEMBER SET PHONE_NUMBER = ? WHERE MEMBER_ID = ?";
	private final String MEMBER_DELETE = "DELETE MEMBER WHERE MEMBER_ID = ?";
	private final String DUPLICATE_CHECK = "SELECT MEMBER_ID FROM MEMBER";

	// 목록 조회
	public void getMemberList() {
		Member mb = new Member();

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_LIST, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("현재 등록된 회원 목록입니다.");
				rs.beforeFirst();
				while (rs.next()) {
					mb.setMemberId(rs.getString("MEMBER_ID"));
					mb.setName(rs.getString("NAME"));
					mb.setPhoneNum(rs.getString("PHONE_NUMBER"));
					System.out.println("--->" + mb.toString());
				}
			} else {
				System.out.println("등록된 회원이 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}

	// 등록
	public void insertMember(String memberId, String name, String phoneNum) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_INSERT);

			stmt.setString(1, memberId);
			stmt.setString(2, name);
			stmt.setString(3, phoneNum);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 수정
	public void updateMember(String phoneNum, String memberId) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_UPDATE);

			stmt.setString(1, phoneNum);
			stmt.setString(2, memberId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 삭제
	public void deleteMember(String memberId) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_DELETE);

			stmt.setString(1, memberId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	 // 회원 상세 목록
    public boolean duplicateId(String member_id)
    {	// 똑같은 값이 있으면 true 리턴
    	try 
    	{
    		conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DUPLICATE_CHECK);
			rs = stmt.executeQuery();
			boolean check = false;
			while(rs.next())
			{
				String id = rs.getString("MEMBER_ID");
				if(id.equals(member_id)) check = true;
			}
			return check;
    	} 
    	catch (SQLException e) 
    	{
    		return false;
    	} 
    	finally 
    	{
    		JDBCUtil.close(stmt, conn);
    	}
    }
}
