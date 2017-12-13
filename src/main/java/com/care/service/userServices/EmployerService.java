package com.care.service.userServices;

import com.care.model.users.Employer;

public interface EmployerService {
    Iterable<Employer> findAllEmployers();
    Employer findEmployerByEmail(String email);
    void saveEmployer(Employer employer);
    void enableEmployer(Employer employer);
}
