package com.care.service.userServices;

import com.care.model.users.Caregiver;
import com.care.model.Role;
import com.care.repository.userRepositories.CaregiverRepository;
import com.care.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("caregiverService")
public class CaregiverServiceImpl implements CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Iterable<Caregiver> findAllCaregivers(){
        return caregiverRepository.findAll();
    }

    @Override
    public Caregiver findCaregiverById(Integer id){
        return caregiverRepository.findById(id);
    }

    @Override
    public Caregiver findCaregiverByEmail(String email){
        return caregiverRepository.findByEmail(email);
    }

    @Override
    public void saveCaregiver(Caregiver caregiver){
        caregiver.setPassword(bCryptPasswordEncoder.encode(caregiver.getPassword()));
        Role caregiverRole = roleRepository.findByRole("CAREGIVER");
        caregiver.setRoles(new HashSet<Role>(Arrays.asList(caregiverRole)));
        caregiverRepository.save(caregiver);
    }

    @Override
    public void enableCaregiver(Caregiver caregiver, boolean status) {
        caregiver.setEnable(status);
        caregiverRepository.save(caregiver);
    }
}
