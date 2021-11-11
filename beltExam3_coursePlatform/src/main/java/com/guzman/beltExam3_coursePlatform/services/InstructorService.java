package com.guzman.beltExam3_coursePlatform.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.guzman.beltExam3_coursePlatform.repositories.*;

import com.guzman.beltExam3_coursePlatform.modules.*;


@Service
public class InstructorService {

	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	
	public Instructor register(Instructor newInstructor, BindingResult result) {
        if(instructorRepository.findByEmail(newInstructor.getEmail()).isPresent()) {
            result.rejectValue("email", "Unique", "This email is already in use!");
        }
        if(!newInstructor.getPassword().equals(newInstructor.getConfirmpassword())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            String hashed = BCrypt.hashpw(newInstructor.getPassword(), BCrypt.gensalt());
            newInstructor.setPassword(hashed);
            return instructorRepository.save(newInstructor);
        }
	}
        
        
        public Instructor login(LoginInstructor newLogin, BindingResult result) {
            if(result.hasErrors()) {
                return null;
            }
            Optional<Instructor> potentialUser = instructorRepository.findByEmail(newLogin.getEmail());
            if(!potentialUser.isPresent()) {
                result.rejectValue("email", "Unique", "Unknown email!");
                return null;
            }
            Instructor instructor = potentialUser.get();
            if(!BCrypt.checkpw(newLogin.getPassword(), instructor.getPassword())) {
                result.rejectValue("password", "Matches", "Invalid Password!");
            }
            if(result.hasErrors()) {
                return null;
            } else {
                return instructor;
            }
        }


		public List<Object[]> findAllCourses() {
			List<Object[]> table = courseRepository.findAllCourses();
			List<Object[]> table2 = new ArrayList<Object[]>();
				for (Object[] row : table) {
					Course courses = new Course();
					courses.setIdcourse(Long.parseLong (row[0].toString())); 
					courses.setName(row[1].toString());
					courses.setDayoftheweek (row[2].toString());
					courses.setPrice(Double.parseDouble(row[3].toString()));
					courses.setTime(row[4].toString());
					Instructor instructorname = new Instructor();
					instructorname.setName(row[5].toString());
					Object[] variable = {courses,instructorname};
					table2.add(variable);
			}
				return table2;

		}




		public Instructor instructorfindById(Instructor instructor) {
			return instructorRepository.findByidinstructor(instructor.getIdinstructor());
		}


		public Course findCourseById(Long idcourse) {
			return courseRepository.findCourseById(idcourse);
		}


		public List<Student> findAllStudentsInCourseByIdCourse(Long idcourse) {
			return studentRepository.findAllStudentsInCourseByIdCourse(idcourse);
		}


		public List<Student> findAllStudentsNotInCourseByIdCourse(Long idcourse, List<Student> studentsInCourse) {
			List<Student> students = studentRepository.findAllStudentsNotInCourseByIdCourse(idcourse);
			List<Student> studentsNotIn = new ArrayList<Student>();
			Boolean flag = false;
			for (Student stn: students)
			{
				flag = false;
				for (Student st: studentsInCourse )
				{
					if (st.getEmail() == stn.getEmail())
					{
						System.out.println (st.getEmail() + " - " + stn.getEmail());
						flag = true;
					}
					
				}
				
				if (flag == false)
				{
					studentsNotIn.add(stn);
					
				}
				
			}
		
			return studentsNotIn;
		}
		
		


		public Instructor findInstructorByIdCourse(Long idcourse) {
			return instructorRepository.findInstructorByIdCourse(idcourse);
		}


		public void addExistingStudent(Long idcourse, Long idinstructor, Long idstudent) {
			Student student = studentRepository.findStudentById(idstudent);
			studentRepository.addStudent(idcourse, idinstructor, student.getName(), student.getEmail());
			
		}


		public void addStudent(Long idcourse, long idinstructor, String name, String email) {
			studentRepository.addStudent(idcourse, idinstructor, name, email);
			
		}
		public Course createCourse (Course newcourse) {
			return courseRepository.save(newcourse);
		}

		public void createCourse(Long idinstructor, String name, String dayoftheweek, String time, double price,
				String description) {
			courseRepository.createCourse(idinstructor, name, dayoftheweek, time, price,description);
			
		}


		public void updateCourseById(Long idcourse, Course editcourse) {
			courseRepository.updateCourse(idcourse, editcourse.getName(), editcourse.getDayoftheweek(), editcourse.getTime(), editcourse.getPrice(), editcourse.getDescription());
		}
		
		public void deleteCourseById(Long id) {
			courseRepository.deleteById(id);
		}

		
}
