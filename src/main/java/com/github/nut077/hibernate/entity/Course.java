package com.github.nut077.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity(name = "courses")
@SequenceGenerator(name = "courses_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"id", "instructor"})
public class Course {

    @Id
    @GeneratedValue(generator = "courses_id")
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Instructor instructor;
}
