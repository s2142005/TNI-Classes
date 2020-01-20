package com.arms.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arms.domain.entity.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	void deleteByProjectId(int projectId);
}
