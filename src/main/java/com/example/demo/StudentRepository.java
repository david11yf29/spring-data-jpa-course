package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findStudentByEmail(String email);

	@Query("SELECT s FROM Student s WHERE s.firstName = :firstName AND s.age >= :age")
	List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(
			@Param("firstName") String firstName,@Param("age") Integer age);

	@Transactional
	@Modifying
	@Query("DELETE FROM Student u WHERE u.id = ?1")
	int deleteStudentById(Long id);
}
