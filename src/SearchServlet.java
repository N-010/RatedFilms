import com.mysoft.FilmInformation;
import com.mysoft.Kinopoisk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by root on 13.12.15.
 */
@WebServlet(name = "SearchServlet", urlPatterns = "/result")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("search");

        if (title.length() == 0) {
            response.sendRedirect("");
            return;
        }

        try {
            List<FilmInformation> filmInformationList = Kinopoisk.getListFilmsByTitle(title);

            if (filmInformationList.size() == 0) {
                response.sendRedirect("");
                return;
            }

            request.setAttribute("filmList", filmInformationList);
        } catch (ClassNotFoundException | SQLException e) {
            response.sendRedirect("");
            return;
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("");
    }
}
