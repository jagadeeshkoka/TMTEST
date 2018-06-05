package com.tm.tmtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tm.tmtest.model.Task;

public interface TaskRepository extends JpaRepository<Task, String> {

}
