package demo.h2database.controller;

import demo.h2database.model.Department;
import demo.h2database.repository.DepartmentRepository;
import demo.h2database.repository.DepartmentRepositoryUdenSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class DepartmentController {

    private DepartmentRepository repository;

    public DepartmentController(DepartmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public String findDepartmentById(Model model) throws SQLException {
        Department found = repository.getDepartment(20);
        model.addAttribute("dept", found);
        return "show";
    }
}
