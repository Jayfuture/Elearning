package in.bushansirgur.springbootthymeleaf.controller;

import in.bushansirgur.springbootthymeleaf.dao.CourseRepository;
import in.bushansirgur.springbootthymeleaf.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository eRepo;

    @GetMapping({"/courseManager"})
    public ModelAndView getAllCourse() {
        ModelAndView mav = new ModelAndView("pages/manager/courseManager");
        mav.addObject("courses", eRepo.findAll());
        return mav;
    }

    @GetMapping("/addCourse")
    public ModelAndView addCourseForm() {
        ModelAndView mav = new ModelAndView("pages/manager/courseEdit");
        Course newCourse = new Course();
        mav.addObject("course", newCourse);
        return mav;
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute Course course) {
        eRepo.save(course);
        return "redirect:pages/manager/courseManager";
    }

    @GetMapping("/showUpdateCourseForm")
    public ModelAndView showUpdateCourseForm(@RequestParam Integer courseId) {
        ModelAndView mav = new ModelAndView("pages/manager/courseEdit");
        Course course = eRepo.findById(courseId).get();
        mav.addObject("course", course);
        return mav;
    }

    @GetMapping("/deleteCourse")
    public String deleteTodo(@RequestParam Integer courseId) {
        eRepo.deleteById(courseId);
        return "redirect:pages/manager/courseManager";
    }
}
