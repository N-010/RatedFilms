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
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            if (id > Main.getNumberLinesDatabase()) {
                response.sendRedirect("");
                return;
            }
        } catch (ClassNotFoundException | SQLException | NumberFormatException e) {
            response.sendRedirect("/");
            return;
        }

        try {
            request.setAttribute("filmInf", Main.getFilmInformationById(id));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        try {
            request.getRequestDispatcher("/WEB-INF/film.jsp").forward(request, response);
        } catch (Throwable e) {
            response.sendRedirect("/");
        }
    }
}
