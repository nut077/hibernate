package com.github.nut077.hibernate.service;

import com.github.nut077.hibernate.entity.Instructor;
import com.github.nut077.hibernate.entity.InstructorDetail;
import com.github.nut077.hibernate.exception.NotFoundException;
import com.github.nut077.hibernate.repository.InstructorDetailRepository;
import com.github.nut077.hibernate.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorDetailRepository instructorDetailRepository;

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor findById(Long id) {
        if (!instructorRepository.findById(id).isPresent()) {
            throw new NotFoundException("Instructor id : " + id + " not found");
        }
        return instructorRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        instructorRepository.deleteById(id);
    }

    public Instructor deleteInstructorDetail(Long id) {
        Instructor instructor = findById(id);
        InstructorDetail instructorDetail = instructor.getInstructorDetail();
        instructor.setInstructorDetail(null);
        instructorDetailRepository.delete(instructorDetail);
        return instructor;
    }
}
