package com.tm.tmtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tm.tmtest.model.Team;

public interface TeamRepository extends JpaRepository<Team, String>{

}
