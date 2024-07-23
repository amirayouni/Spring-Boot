package HRnet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import HRnet.entity.Course.Course;;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseName(String courseName);
}