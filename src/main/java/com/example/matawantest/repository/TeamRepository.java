package com.example.matawantest.repository;

import com.example.matawantest.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findByName(String name);

    Team findById(Long id);
}
