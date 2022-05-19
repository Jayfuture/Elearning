package in.bushansirgur.springbootthymeleaf.controller;

import in.bushansirgur.springbootthymeleaf.dao.TeacherRepository;
import in.bushansirgur.springbootthymeleaf.entity.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor

public class TeacherController {

    //private TeacherService teacherService;

    @Autowired
    TeacherRepository teacherRepos;

//    public TeacherController(TeacherService teacherService) {
//        this.teacherService = teacherService;
//    }

    @GetMapping("/register")
    ModelAndView registerUser() {
        ModelAndView mav = new ModelAndView("/pages/user/register");
        Teacher newteacher = new Teacher();
        mav.addObject("teacher", newteacher);
        return mav;
    }

    @PostMapping("/register")
    String saveTeacher(@Valid @ModelAttribute Teacher teacher, BindingResult result, Principal principal) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(teacher.getTeacherPassword());
        teacher.setTeacherPassword(encodedPassword);
        teacherRepos.save(teacher);
        return "register-success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/users")
//    public ModelAndView listUsers() {
//        ModelAndView mav = new ModelAndView("list-users");
//        mav.addObject("users", teacherRepos.findAll());
//        return mav;
//    }
}