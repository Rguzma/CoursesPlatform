package com.guzman.beltExam3_coursePlatform.repositories;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.guzman.beltExam3_coursePlatform.modules.*;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
	
	Optional<Instructor> findByEmail(String email);
	Instructor save(Instructor instructor);
	@Query(value="select idinstructor, createdat, email, name, password, updatedat from instructors where idinstructor = ?1",  nativeQuery=true)
	Instructor findByidinstructor(Long idinstructor);

	@Query(value="select i.idinstructor, i.createdat, i.email, i.name, i.password, i.updatedat from instructors i inner join courses c on i.idinstructor = c.idinstructedby where c.idcourse = ?1",  nativeQuery=true)
	Instructor findInstructorByIdCourse(Long idcourse);
}
