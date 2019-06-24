package com.github.nut077.hibernate;

import com.github.nut077.hibernate.entity.Instructor;
import com.github.nut077.hibernate.entity.InstructorDetail;
import com.github.nut077.hibernate.repository.InstructorDetailRepository;
import com.github.nut077.hibernate.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class HibernateApplication implements CommandLineRunner {

    private final InstructorRepository instructorRepository;
    private final InstructorDetailRepository instructorDetailRepository;

    public static void main(String[] args) {
        SpringApplication.run(HibernateApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Instructor instructor = Instructor.builder()
                .firstName("Nut")
                .lastName("Freedom")
                .email("nut@mail.com")
                .instructorDetail(InstructorDetail.builder()
                        .youtubeChannel("https://www.youtube.com/freedom")
                        .hobby("football").build()).build();

        Instructor instructor2 = Instructor.builder()
                .firstName("Rican")
                .lastName("Freedom")
                .email("nut@mail.com")
                .instructorDetail(InstructorDetail.builder()
                        .youtubeChannel("https://www.youtube.com/rican")
                        .hobby("cartoon").build()).build();

        Instructor instructor3 = Instructor.builder()
                .firstName("Arashi")
                .lastName("Freedom")
                .email("nut@mail.com")
                .instructorDetail(InstructorDetail.builder()
                        .youtubeChannel("https://www.youtube.com/arashi")
                        .hobby("animal").build()).build();

        instructorRepository.save(instructor);
        instructorRepository.save(instructor2);
        instructorRepository.save(instructor3);

        // delete child
        Instructor instructorDeleteChild = null;
        if (instructorRepository.findById(2L).isPresent()) {
            instructorDeleteChild = instructorRepository.findById(2L).get();
        }
        if (instructorDeleteChild != null) {
            InstructorDetail instructorDetailDelete = instructorDeleteChild.getInstructorDetail();
            instructorDeleteChild.setInstructorDetail(null);
            instructorRepository.save(instructorDeleteChild);
            instructorDetailRepository.delete(instructorDetailDelete);
        }

        // delete self
        Instructor instructorDeleteSelf = null;
        if (instructorRepository.findById(3L).isPresent()) {
            instructorDeleteSelf = instructorRepository.findById(3L).get();
        }
        if (instructorDeleteSelf != null) {
            instructorRepository.delete(instructorDeleteSelf);
        }
    }
}
