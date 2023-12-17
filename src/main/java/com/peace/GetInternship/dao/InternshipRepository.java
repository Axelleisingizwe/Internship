package com.peace.GetInternship.dao;






import com.peace.GetInternship.model.Internship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InternshipRepository extends PagingAndSortingRepository<Internship, Long> {
    //searching using a keyword
    @Query("SELECT i FROM Internship i WHERE "
            + "CONCAT(i.title,i.company,i.location, i.startDate, i.endDate," +
            " i.description, i.requirements)" + "LIKE %?1%")
    public Page<Internship> findAll(String keyword, Pageable pageable);
}
