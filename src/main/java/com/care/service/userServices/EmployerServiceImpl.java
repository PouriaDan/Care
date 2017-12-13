package com.care.service.userServices;

import com.care.model.users.Employer;
import com.care.model.Role;
import com.care.model.verification.VerificationToken;
import com.care.repository.userRepositories.EmployerRepository;
import com.care.repository.RoleRepository;
import com.care.repository.verificationRepository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("employerService")
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private VerificationTokenRepository tokenRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        employer.setPassword(bCryptPasswordEncoder.encode(employer.getPassword()));
        Role employerRole = roleRepository.findByRole("EMPLOYER");
        employer.setRoles(new HashSet<Role>(Arrays.asList(employerRole)));
        employerRepository.save(employer);
    }

    @Override
    public void enableEmployer(Employer employer) {
        employer.setEnable(true);
        employerRepository.save(employer);
    }
}
