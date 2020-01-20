package com.arms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arms.domain.entity.Hello;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Integer> {

}
