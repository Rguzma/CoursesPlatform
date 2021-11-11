package com.guzman.beltExam3_coursePlatform.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guzman.beltExam3_coursePlatform.services.*;

import com.guzman.beltExam3_coursePlatform.modules.*;
import com.guzman.beltExam3_coursePlatform.validator.*;
@Controller
@RequestMapping("/")
public class InstructorController {
	
    @Autowired
    private InstructorService instructorService;
    
private final InstructorValidator instructorValidator;
    
    // NEW
    public InstructorController(InstructorValidator instructorValidator) {
        this.instructorValidator = instructorValidator;
    }
    
    
	public Long userSessionId(HttpSession session) {
		if(session.getAttribute("idinstructor") == null) {
			return null;
		}
		else {
		return (Long)session.getAttribute("idinstructor");
		}
	}
    
    @GetMapping("/")
    public String index(@ModelAttribute("newinstructor") Instructor instructor, @ModelAttribute("newlogin")LoginInstructor newLogin, Model model) {
        model.addAttribute("newInstructor", new Instructor());
        model.addAttribute("newLogin", new LoginInstructor());
        return "Index.jsp";
    }
 
	@PostMapping("register/instructor")
	public String Register(@Valid @ModelAttribute("newinstructor")  Instructor newInstructor, @ModelAttribute("newlogin") LoginInstructor newLogin,  BindingResult result, Model model, HttpSession session) {
		instructorService.register(newInstructor, result);
		if(result.hasErrors()) {
			instructorValidator.validate(newInstructor, result);
			model.addAttribute("newInstructor", new LoginInstructor());
			return "Index.jsp";
		}
        session.setAttribute("idinstructor", newInstructor.getIdinstructor());
        return "redirect:/home";
	}
	@PostMapping("/validate/instructor")
	public String Login(@Valid @ModelAttribute("newlogin") LoginInstructor newLogin, @ModelAttribute("newinstructor")  Instructor newInstructor, BindingResult result, Model model, HttpSession session) {
		System.out.println (newLogin);
		Instructor instructor = instructorService.login(newLogin, result);
		if(result.hasErrors()) {
			model.addAttribute("newInstructor", new Instructor());
			return "Index.jsp";
		}
		session.setAttribute("idinstructor", instructor.getIdinstructor());
		return "redirect:/home";
	}
	
    @GetMapping("/home")
    public String home( Model model, HttpSession session) {
    	Long idinstructor = this.userSessionId(session);
		if(idinstructor == null) {
			return "redirect:/";
		}
		else {
    	Instructor instructor = new Instructor();
    	//System.out.println ("session " + session.getAttribute("idinstructor"));
    	instructor.setIdinstructor((Long)session.getAttribute("idinstructor"));
    	
        instructor = instructorService.instructorfindById(instructor);
        System.out.println ("devuelve al instructor");
    	List<Object[]> courses = instructorService.findAllCourses();
    	model.addAttribute("courses", courses);
    	model.addAttribute("instructor", instructor);
        return "Home.jsp";
		}
    }
    
    @GetMapping("create/newCourse")
    public String createCourse(@ModelAttribute ("newcourse")Course newcourse, Model model, HttpSession session) {
    	Long userId = this.userSessionId(session);
		if(userId == null) {
			return "redirect:/";
		}
		else {
    return "newPage.jsp";
		}
    }
    
    @PostMapping("/new/course")
    public String createNewCourse(@Valid @ModelAttribute ("newcourse")Course newcourse, Model model, HttpSession session) {
    	Long idinstructor = Long.parseLong(session.getAttribute("idinstructor").toString());
    	instructorService.createCourse(idinstructor, newcourse.getName(), newcourse.getDayoftheweek(),
    			newcourse.getTime(), newcourse.getPrice(), newcourse.getDescription());
    	return "redirect:/home";
    }
    @GetMapping("edit/course/{idcourse}")
    public String editCourse(@PathVariable("idcourse")Long idcourse, @ModelAttribute ("editcourse")Course editcourse, @ModelAttribute ("deletecourse")Course deletecourse, Model model, HttpSession session) {
    	Long userId = this.userSessionId(session);
		if(userId == null) {
			return "redirect:/";
		}
		else {
    	Course course = instructorService.findCourseById( idcourse);
		model.addAttribute("course", course);
    	return "EditCourse.jsp";
		}
    }
    
	@RequestMapping(value = "/edit/course/{idcourse}" , method = RequestMethod.POST)
	public String updateCourse(@PathVariable("idcourse")Long idcourse, Model model, @ModelAttribute ("editcourse") Course editcourse, BindingResult result) {
		instructorService.updateCourseById(idcourse, editcourse);
		model.addAttribute("course", idcourse);
		return "redirect:/home";
	 }
	@PostMapping(value = "/delete/course/{idcourse}")
	public String deleteCourse(@PathVariable("idcourse")Long idcourse, Model model, @ModelAttribute ("deletecourse") Course deletecourse, BindingResult result) {
		instructorService.deleteCourseById(idcourse);
		model.addAttribute("course", idcourse);
		return "redirect:/home";
	 }
    
    @GetMapping("/courses/{idcourse}")
    public String detailsCourse(@ModelAttribute ("stdentsnot")Student stdentsnot, @ModelAttribute ("addstudent")Student addstudent, @PathVariable("idcourse")Long idcourse, Model model, HttpSession session) {
    	Long idinstructor = this.userSessionId(session);
		if(idinstructor == null) {
			return "redirect:/";
		}
		else {
    	Course course = instructorService.findCourseById(idcourse);	
    	model.addAttribute("course", course);
    	List<Student> studentsInCourse = instructorService.findAllStudentsInCourseByIdCourse(idcourse);	
    	model.addAttribute("studentsincourse", studentsInCourse);
    	List<Student> studentsNotInCourse = instructorService.findAllStudentsNotInCourseByIdCourse(idcourse, studentsInCourse);	
    	model.addAttribute("studentsnotincourse", studentsNotInCourse);
    	Instructor instructor = instructorService.findInstructorByIdCourse(idcourse);	
    	model.addAttribute("instructor", instructor);
    	return "/CourseDetails.jsp";
		}
    }

    
    @PostMapping("/add/existing/student/{idcourse}/{idinstructor}")
    public String addExistingStudent(@ModelAttribute ("instructor")Instructor instructor, @ModelAttribute ("stdentsnot")Student stdentsnot, @ModelAttribute ("addstudent")Student addstudent, @PathVariable("idcourse")Long idcourse, @PathVariable("idinstructor")Long idinstructor) {
    	System.out.println (stdentsnot.getIdstudent());
    	System.out.println (instructor.getIdinstructor());
    	instructorService.addExistingStudent(idcourse, idinstructor, stdentsnot.getIdstudent());
    	return "redirect:/courses/" + idcourse;
    }

    @PostMapping("/add/student/{idcourse}/{idinstructor}")
    public String addStudent(@ModelAttribute ("instructor")Instructor instructor, @ModelAttribute ("stdentsnot")Student stdentsnot, @ModelAttribute ("addstudent")Student addstudent, @PathVariable("idcourse")Long idcourse, @PathVariable("idinstructor")Long idinstructor) {
    	System.out.println (idinstructor);
    	System.out.println (addstudent.getName());
    	System.out.println (addstudent.getEmail());
    	instructorService.addStudent(idcourse, idinstructor, addstudent.getName(), addstudent.getEmail());
    	return "redirect:/courses/" + idcourse;
    }
    
    
	@RequestMapping("/reset")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
    
}
    
    


