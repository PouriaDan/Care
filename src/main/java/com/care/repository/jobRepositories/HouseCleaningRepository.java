package com.care.repository.jobRepositories;

import com.care.model.jobs.HouseCleaning;
import org.springframework.stereotype.Repository;

@Repository("houseCleaningRepository")
public interface HouseCleaningRepository extends BaseJobRepository<HouseCleaning> {
}
