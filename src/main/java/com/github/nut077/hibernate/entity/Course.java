package com.github.nut077.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "courses")
@SequenceGenerator(name = "courses_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"id", "instructor", "reviews"})
public class Course {

    @Id
    @GeneratedValue(generator = "courses_id")
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Instructor instructor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;
}
