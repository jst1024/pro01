package org.balsan.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.balsan.dto.Notice;

public class OracleTest {
	public static void main(String[] args) {
		Connection con = null;	//����
		PreparedStatement pstmt = null;	//���� ����(ON/OFF) �ϰ�, SQL ���� ����
		ResultSet rs = null;	//�˻�(Select��)�� ����� ��ȯ����
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "system";
		String userpw = "1234";
		String sql = "select * from notice";
		List<Notice> notiList = new ArrayList<>();
		try {
			Class.forName(driver);	//���� Ŭ������ ����̹� �ε�
			try {
				con = DriverManager.getConnection(url, userid, userpw); //�����ͺ��̽� ������ ����
				pstmt = con.prepareStatement(sql); //���� ä�� ����
				rs = pstmt.executeQuery();	//sql ������ select ������ ��쿡�� commit�� �ʿ� �����Ƿ� executeQuery() �޼ҵ带 ����ϸ�, ��� ��ȯ�� ���ڵ��(ResultSet)�̴�. 
				while(rs.next()) {
					Notice noti = new Notice(rs.getInt("no"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getString("resdate"),
							rs.getInt("visited"));
					notiList.add(noti);
				}
			} catch (SQLException e) {
				System.out.println("�����ͺ��̽� ���� ���� �Ǵ� SQL ���� ���� ����");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("�����ͺ��̽� �ε� ����");
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		for(Notice n:notiList) {
			System.out.println(n.toString());
		}
	}
}