package me.lovecc.web.controller;

import me.lovecc.domain.User;
import utils.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet",urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setUsername(username);
            System.out.println("把用户信息注册到数据库中");
            Thread thread = new Thread(new SendEmail(user));
            thread.start();
            request.setAttribute("message", "恭喜您，注册成功，我们已经发了一封带了注册信息的电子邮件，请查收，如果没有收到，可能是网络原因，过一会儿就收到了！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("message", "注册失败！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
