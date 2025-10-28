package com.example.proiect.CinemaApp.repository;

import com.example.proiect.CinemaApp.model.Staff;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class Staff_Repo {
    private final Map<String, Staff> staffMembers = new HashMap<>();

    public List<Staff> findAll() {
        return new ArrayList<>(staffMembers.values());
    }

    public Optional<Staff> findById(String id) {
        return Optional.ofNullable(staffMembers.get(id));
    }

    public Staff save(Staff staff) {
        staffMembers.put(staff.getId(), staff);
        return staff;
    }

    public void deleteById(String id) {
        staffMembers.remove(id);
    }
}

