package com.guzman.beltExam3_coursePlatform.repositories;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.guzman.beltExam3_coursePlatform.modules.*;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

	@Query(value="select c.idcourse, c.name, c.dayoftheweek, c.price, c.time, i.name as iname from courses c inner join instructors i on c.idinstructedby = i.idinstructor",  nativeQuery=true)
	List<Object[]> findAllCourses();

	@Query(value="select * from courses where idcourse = ?1",  nativeQuery=true)	
	Course findCourseById(Long idcourse);

	Course save( Course newcourse );

	@Modifying
	@Transactional
	@Query(value="insert into courses (createdat, dayoftheweek, description, name, price, time, updatedat, idinstructedby) values (now(), ?3, ?6, ?2, ?5, ?4, now(), ?1)",  nativeQuery=true)	
	void createCourse(Long idinstructor, String name, String dayoftheweek, String time, double price, String description);

	@Modifying
	@Transactional
	@Query(value="update courses set name = ?2, dayoftheweek = ?3, time = ?4, price = ?5, description = ?6 where idcourse = ?1",  nativeQuery=true)	
	void updateCourse(Long idcourse, String name, String dayoftheweek, String time, double price, String description);
	

}
