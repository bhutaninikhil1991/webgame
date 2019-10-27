package com.example.appengine.java8;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MasterMindServlet", value = "/mastermind")
public class MasterMindServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get player info
        String first = request.getParameter("first");
        String last = request.getParameter("last");

        HttpSession session = request.getSession();

        //save player info in session
        session.setAttribute("name", last + ", " + first);

        request.getRequestDispatcher("/mastermind.jsp").forward(request, response);
    }
}
