import com.mysoft.Database;
import com.mysoft.FilmInformation;
import com.mysoft.Kinopoisk;

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
        FilmInformation filmInformation;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            if (id > Database.getNumberLinesDatabase()) {
                response.sendRedirect("");

                return;
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            response.sendRedirect("/");

            return;
        }

        try {
            String codePlayer;
            filmInformation = Kinopoisk.getFilmInformationById(id);
            codePlayer = filmInformation.getCodePlayer();
            request.setAttribute("filmInf", filmInformation);
            if (codePlayer == null)
                codePlayer = "";

            request.setAttribute("codePlayer", codePlayer);

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
