import com.mysoft.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by root on 12.12.15.
 */
@WebServlet(name = "FilmServlet", urlPatterns = "/film")
public class FilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));

            if (id > 250)
                throw new NumberFormatException("id > 250");

        } catch (NumberFormatException e) {
            System.out.println(e.toString());
            response.sendRedirect("/");
        }
        try {
            request.setAttribute("filmInf", Main.getFilmInformationById(id));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            request.getRequestDispatcher("/WEB-INF/film.jsp").forward(request, response);
        } catch (Throwable e) {
            response.sendRedirect("/");
        }



      /*  try {
            request.setAttribute("filmInf", Main.getFilmInformationById(id));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/film.jsp").forward(request, response);*/
    }
}
