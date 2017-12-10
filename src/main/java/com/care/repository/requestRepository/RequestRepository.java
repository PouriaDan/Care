package com.care.repository.requestRepository;

import com.care.model.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("requestRepository")
public interface RequestRepository extends JpaRepository<Request, Long>{
}
