package com.care.service.userServices;

import com.care.model.users.Caregiver;

public interface CaregiverService {
    Iterable<Caregiver> findAllCaregivers();
    Caregiver findCaregiverByEmail(String email);
    void saveCaregiver(Caregiver caregiver);
}
