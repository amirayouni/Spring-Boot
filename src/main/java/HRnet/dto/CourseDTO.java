package HRnet.dto;

import HRnet.entity.Course.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
    private Long courseId;
    private String courseName;
    private CourseStatus status;
    private String duration;
   


    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseStatus getStatus() {
        return status;
    }
    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public String getDuration(){
        return duration;
    }
    public void setDuration(String duration){
        this.duration=duration;
    }


}
