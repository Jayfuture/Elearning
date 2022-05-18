package in.bushansirgur.springbootthymeleaf.controller;

import in.bushansirgur.springbootthymeleaf.dao.TeacherRepository;
import in.bushansirgur.springbootthymeleaf.entity.Teacher;
import in.bushansirgur.springbootthymeleaf.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor

public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    TeacherRepository teacherRepos;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/register")
    ModelAndView registerUser() {
        ModelAndView mav = new ModelAndView("register-form");
        Teacher newteacher = new Teacher();
        mav.addObject("teacher", newteacher);
        return mav;
    }

    @PostMapping("/register")
    String saveTeacher(@Valid @ModelAttribute Teacher teacher, BindingResult result) {
        // Check if username exists
        if (teacherService.usernameExists(teacher.getTeacherName())) {
            result.addError(new FieldError("user", "username", "Username already exists, please choose another one"));
        }

        // Authenticate username
        String errorMsg = teacherService.authenticateUsername(teacher.getTeacherName());
        if (!errorMsg.isEmpty()) {
            result.addError(new FieldError("user", "username", errorMsg));
        }

        // Check if email exists
        if (teacherService.emailExists(teacher.getTeacherEmail())) {
            result.addError(new FieldError("user", "email", "Email already exists, please choose another email"));
        }

        // Authenticate password
        List<String> errorMsgs = teacherService.authenticatePassword(teacher.getTeacherPassword());
        if (!errorMsgs.isEmpty()) {
            for (String msg : errorMsgs) {
                result.addError(new FieldError("user", "password", msg));
            }
        }

        // Check if both passwords are the same
        if (!teacherService.checkConfirmPassword(teacher.getTeacherPassword(), teacher.getConfirmPassword())) {
            result.addError(new FieldError("user", "confirmPassword", "The 2 passwords don't match."));
        }

        if (result.hasErrors()) {
            return "register-user-form";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(teacher.getTeacherPassword());
        teacher.setTeacherPassword(encodedPassword);
        teacherRepos.save(teacher);
        return "register-success";
    }

    @GetMapping("/users")
    public ModelAndView listUsers() {
        ModelAndView mav = new ModelAndView("list-users");
        mav.addObject("users", teacherRepos.findAll());
        return mav;
    }
}