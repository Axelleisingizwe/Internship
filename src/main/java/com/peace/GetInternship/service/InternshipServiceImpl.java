package com.peace.GetInternship.service;


import com.peace.GetInternship.dao.InternshipRepository;
import com.peace.GetInternship.model.Internship;
import com.peace.GetInternship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class InternshipServiceImpl implements InternshipService {

    private InternshipRepository internshipRepository;
    private UserService userService;

    private EntityManager entityManager;

    @Autowired
    public InternshipServiceImpl(InternshipRepository theInternshipRepository, UserService theuserService, EntityManager theentityManager) {
        internshipRepository = theInternshipRepository;
        this.userService = theuserService;
        this.entityManager = theentityManager;
    }

    @Override
    public List<Internship> findAll() {
        // create query
        TypedQuery<Internship> theQuery = entityManager.createQuery("SELECT i FROM Internship i ORDER BY i.title ASC", Internship.class);

        // return query results
        return theQuery.getResultList();
    }


    // Pageable findAll

    @Override
    public Page<Internship> findAll(int pageNumber, String keyword) {
        Sort sort = Sort.by("title").ascending();

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        if(keyword !=null){
            return internshipRepository.findAll(keyword, pageable);
        }
        return internshipRepository.findAll(pageable);
    }


    @Override
    public Internship findById(Long theId) {
        Optional<Internship> result = internshipRepository.findById(theId);

        Internship internship = null;

        if (result.isPresent()) {
            internship = result.get();
        }
        else {
            // we didn't find the project
            throw new RuntimeException("Did not find internship id - " + theId);
        }

        return internship;
    }


    @Override
    @Transactional
    public void save(Internship internship, Long UserId) {
        User existingUser = userService.findById(UserId);
        existingUser.add(internship);
        internshipRepository.save(internship);
    }

    @Override
    public void deleteById(Long theId) {
        internshipRepository.deleteById(theId);
    }
}
