package com.koreait.board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/write")
public class BoardWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //jsp 파일과 연결해주는 부문
        String path = "/WEB-INF/jsp/write.jsp";
        RequestDispatcher pd = req.getRequestDispatcher(path);
        pd.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");
        String writer = req.getParameter("writer");

        System.out.println("title : " + title);
        System.out.println("ctnt : " + ctnt);
        System.out.println("writer : " + writer);

        BoardVO param = new BoardVO();
        param.setTitle(title);
        param.setCtnt(ctnt);
        param.setWriter(writer);

        int result = BoardDAO.insBoard(param);
        switch (result){
            case 1:
                res.sendRedirect("/list");
                break;
            default:
                res.sendRedirect("/write");
                break;
        }
    }
}