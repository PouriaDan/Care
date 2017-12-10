package com.care.service.userServices;

import com.care.model.users.Caregiver;
import com.care.model.Role;
import com.care.repository.userRepositories.CaregiverRepository;
import com.care.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("caregiverService")
public class CaregiverServiceImpl implements CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Caregiver> findAllCaregivers(){
        return caregiverRepository.findAll();
    }

    @Override
    public Caregiver findCaregiverByEmail(String email){
        return caregiverRepository.findByEmail(email);
    }

    @Override
    public void saveCaregiver(Caregiver caregiver){
        Role caregiverRole = roleRepository.findByRole("CAREGIVER");
        caregiver.setRoles(new HashSet<Role>(Arrays.asList(caregiverRole)));
        caregiver.setEnable(1);
        caregiverRepository.save(caregiver);
    }
}
