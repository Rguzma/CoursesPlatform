package com.guzman.beltExam3_coursePlatform.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.guzman.beltExam3_coursePlatform.modules.*;


@Repository
public interface StudentRepository extends CrudRepository<Student, Long>  {

	@Query(value="select idinstructor, idcourse, idstudent, createdat, email, name, updatedat from students where idcourse = ?1",  nativeQuery=true)
	List<Student> findAllStudentsInCourseByIdCourse(Long idcourse);

	@Query(value="select distinct idinstructor, idcourse, idstudent, createdat, email, name, updatedat \r\n"
			+ "from students \r\n"
			+ "where idcourse != ?1\r\n"
			+ "and name not in (select distinct name from students where idcourse = ?1)",  nativeQuery=true)
	List<Student> findAllStudentsNotInCourseByIdCourse(Long idcourse);

	@Query(value="select idinstructor, idcourse, idstudent, createdat, email, name, updatedat from students where idstudent = ?1",  nativeQuery=true)
	Student findStudentById(Long idstudent);
	
	@Modifying
	@Transactional
	@Query(value="insert into students (createdat, email, name, updatedat, idcourse, idinstructor) values (now(), ?4, ?3, now(), ?1, ?2)",  nativeQuery=true)
	void addStudent(Long idcourse, Long idinstructor, String name, String email);


}
