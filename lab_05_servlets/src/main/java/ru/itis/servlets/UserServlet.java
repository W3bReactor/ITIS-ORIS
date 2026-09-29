package ru.itis.servlets;

import ru.itis.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private List<User> users;


    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();

        // Builder позволяет заполнять не все поля сразу
        User userOne = User.builder()
                .id(1L)
                .name("Insaf")
                .surname("Garifullin")
                .age(18)
                .build();

        User userTwo = User.builder()
                .id(2L)
                .name("Danil")
                .surname("Smirnov")
                .age(18)
                .build();

        User userThree = User.builder()
                .id(3L)
                .name("Marat")
                .surname("Valik")
                .age(18)
                .build();

        users.add(userOne);
        users.add(userTwo);
        users.add(userThree);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Позволяет написать HTML-код
        PrintWriter writer = response.getWriter();

        StringBuilder resultHtml = new StringBuilder();

        resultHtml.append("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Users</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Users</h1>\n" +
                "<table>\n" +
                "    <thead>\n" +
                "        <tr>\n" +
                "            <th>ID</th>\n" +
                "            <th>FIRST NAME</th>\n" +
                "            <th>LAST NAME</th>\n" +
                "            <th>AGE</th>\n" +
                "            <th>ID</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "    <tbody>");
        for (User user : users) {
            resultHtml.append("<tr>\n");
            resultHtml.append("<td>").append(user.getId()).append("</td>\n");
            resultHtml.append("<td>").append(user.getName()).append("</td>\n");
            resultHtml.append("<td>").append(user.getSurname()).append("</td>\n");
            resultHtml.append("<td>").append(user.getAge()).append("</td>\n");
            resultHtml.append("</tr>\n");
        }
        resultHtml.append("</tbody>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>");

        writer.write(resultHtml.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

}
