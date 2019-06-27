package com.github.nut077.hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity(name = "instructor_details")
@SequenceGenerator(name = "instructor_details_seq")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"id", "instructor"})
public class InstructorDetail {

    @Id
    @GeneratedValue(generator = "instructor_details_seq")
    private Long id;

    private String youtubeChannel;
    private String hobby;

    @OneToOne(mappedBy = "instructorDetail",
    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private Instructor instructor;
}
