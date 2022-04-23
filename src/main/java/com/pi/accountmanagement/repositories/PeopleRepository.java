package com.pi.accountmanagement.repositories;

import com.pi.accountmanagement.domains.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PeopleRepository extends JpaRepository<People, Long>{


}
