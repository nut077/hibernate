package com.github.nut077.hibernate.controller;

import com.github.nut077.hibernate.entity.Instructor;
import com.github.nut077.hibernate.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> instructors() {
        return ResponseEntity.ok(instructorService.findAll());
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<Instructor> instructor(@PathVariable Long id) {
        return ResponseEntity.ok(instructorService.findById(id));
    }

    @PostMapping("/instructors")
    public ResponseEntity<Instructor> create(@RequestBody Instructor instructor) {
        return ResponseEntity.ok(instructorService.create(instructor));
    }

    @PutMapping("/instructors/{id}")
    public ResponseEntity<Instructor> update(@PathVariable Long id, @RequestBody Instructor instructor) {
        return ResponseEntity.ok(instructorService.update(id, instructor));
    }

    @DeleteMapping("/instructors/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteById(id);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/instructors-detail/{id}")
    public ResponseEntity<Instructor> deleteInstructorDeteail(@PathVariable Long id) {
        Instructor instructor = instructorService.deleteInstructorDetail(id);
        return ResponseEntity.ok(instructor);
    }
}
