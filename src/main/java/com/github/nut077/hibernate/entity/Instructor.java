package com.github.nut077.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "instructors")
@SequenceGenerator(name = "instructors_seq")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "courses")
@Table(indexes = {
        @Index(name = "instructors_idx_first_name", columnList = "firstName")
})
public class Instructor {

    @Id
    @GeneratedValue(generator = "instructors_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Course> courses;

    public void setCourses(List<Course> courses) {
        courses.forEach(course -> course.setInstructor(this));
        this.courses = courses;
    }
}
