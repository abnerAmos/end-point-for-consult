package com.example.demo.repository;

import com.example.demo.model.Dependents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentsRepository extends JpaRepository<Dependents, Integer> {

}
