package pl.sda.mg.webappka.controller;


import pl.sda.mg.webappka.database.EntityDao;
import pl.sda.mg.webappka.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students") // <- nie zapomnij o slash'u na początku, bo nie zadziała.
// <- nie zapomnij o adnotacji, bo nie zadziała.
public class StudentListServlet extends HttpServlet {
    private final EntityDao<Student> studentEntityDao = new EntityDao<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // przetwarzamy zapytanie.

        // pobieramy z bazy listę studentów, ładujemy je do atrybutów (żeby wyświetlić w html/jsp)
        List<Student> listOfStudents = studentEntityDao.findAll(Student.class);
        req.setAttribute("students", listOfStudents);


        // przekazujemy do request dispatcher'a, żeby dla nas przetworzył stronę "student_list"
        // i zwrócił ją do użytkownika
        req.getRequestDispatcher("/student_list.jsp").forward(req, resp);
    }
}
