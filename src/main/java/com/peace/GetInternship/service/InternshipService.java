package com.peace.GetInternship.service;


import com.peace.GetInternship.model.Internship;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InternshipService {
    Page<Internship> findAll(int pageNumber, String keyword);



    Internship findById(Long theId);

    void save(Internship theInternship, Long userId);

    void deleteById(Long theId);

    List<Internship> findAll();
}
