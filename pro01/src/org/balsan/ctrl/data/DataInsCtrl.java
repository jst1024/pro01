package org.balsan.ctrl.data;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.balsan.dao.DatafileDAO;
import org.balsan.dto.Data;

@WebServlet("/DataIns.do")
public class DataInsCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DataInsCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Data data = new Data();
		
		//String title = request.getParameter("title");
		//String content = request.getParameter("content");
		
		data.setTitle(request.getParameter("title"));
		data.setContent(request.getParameter("content"));
		data.setDatafile(request.getParameter("datafile"));
		
		DatafileDAO dao = new DatafileDAO();
		int cnt = dao.insData(data);
		
		if(cnt>0) {
			System.out.println("자료실 업데이트");
		} else {
			System.out.println("자료실 업데이트 실패");
		}
		/*
		List<Notice> notiList = new ArrayList<>();
		notiList = dao.getNoticeList();
		request.setAttribute("notiList", notiList);		
		RequestDispatcher view = request.getRequestDispatcher("/notice/noticeList.jsp");
		view.forward(request, response);
		*/
		ServletContext application = request.getServletContext();
		String home = application.getContextPath();
		if(cnt>0) {
			response.sendRedirect(home+"/DataList.do");
		} else {
			response.sendRedirect(home+"/data/data_ins.jsp");
		}
	}

}