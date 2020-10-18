package pl.sda.mg.webappka.controller;

import pl.sda.mg.webappka.database.EntityDao;
import pl.sda.mg.webappka.model.Grade;
import pl.sda.mg.webappka.model.GradeSubject;
import pl.sda.mg.webappka.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/grade/form")
public class GradeFormServlet extends HttpServlet {
    private final EntityDao<Grade> gradeEntityDao = new EntityDao<>();
    private final EntityDao<Student> studentEntityDao = new EntityDao<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentIdString = req.getParameter("studentId");
        if(studentIdString == null){
            redirectToStudentList(req, resp);
            return;
        }
        Long studentId = Long.parseLong(studentIdString);
        Optional<Student> studentOptional = studentEntityDao.findById(studentId, Student.class);
        if (studentOptional.isPresent()) { // jeśli student, któremu mamy dodać ocenę istnieje
            GradeSubject[] subjects = GradeSubject.values();

            req.setAttribute("studentIdAttribute", studentId);
            req.setAttribute("availableSubjects", subjects);
            req.getRequestDispatcher("/grade_form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Grade grade = new Grade();
        grade.setValue(Double.parseDouble(req.getParameter("value_field")));
        grade.setSubject(GradeSubject.valueOf(req.getParameter("subject_field")));


    }

    private void redirectToStudentList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}