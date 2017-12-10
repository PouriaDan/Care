package com.care.service.userServices;

import com.care.model.users.Employer;
import com.care.model.Role;
import com.care.repository.userRepositories.EmployerRepository;
import com.care.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("employerService")
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Employer> findAllEmployers(){
        return employerRepository.findAll();
    }

    @Override
    public Employer findEmployerByEmail(String email){
        return employerRepository.findByEmail(email);
    }

    @Override
    public void saveEmployer(Employer employer){
        Role employerRole = roleRepository.findByRole("EMPLOYER");
        employer.setRoles(new HashSet<Role>(Arrays.asList(employerRole)));
        employer.setEnable(1);
        employerRepository.save(employer);
    }

    @Override
    public void updateEmployer(Employer employerExist, Employer employer) {
        employerExist.setFirstName(employer.getFirstName());
        employerExist.setLastName(employer.getLastName());
        employerExist.setNumber(employer.getNumber());
        employerExist.setPostalCode(employer.getPostalCode());
        employerExist.setCity(employer.getCity());
        employerExist.setAddress(employer.getAddress());
        saveEmployer(employerExist);
    }
}
