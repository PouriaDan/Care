package com.care.repository.jobRepositories;

import com.care.model.jobs.Job;
import com.care.model.users.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface BaseJobRepository<T extends Job> extends JpaRepository<T, Long> {
    T findById(Integer id);
}
