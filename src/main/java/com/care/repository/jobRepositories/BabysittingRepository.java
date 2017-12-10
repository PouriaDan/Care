package com.care.repository.jobRepositories;

import com.care.model.jobs.BabySitting;
import org.springframework.stereotype.Repository;

@Repository("babysittingRepository")
public interface BabysittingRepository extends BaseJobRepository<BabySitting> {
}
