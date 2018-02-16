package com.care.service.userServices;

import com.care.model.users.Caregiver;

public interface CaregiverService {
    Iterable<Caregiver> findAllCaregivers();
    Caregiver findCaregiverById(Integer id);
    Caregiver findCaregiverByEmail(String email);
    void saveCaregiver(Caregiver caregiver);
    void enableCaregiver(Caregiver caregiver, boolean status);
}
