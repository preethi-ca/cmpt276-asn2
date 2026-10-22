package com.cmpt276.asn2;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StaffRatingRepository extends JpaRepository<StaffRating, Integer> {
    // List<StaffRating> findByName(List<StaffRating> findByName(String name));
    // List<StaffRating> findByNameAndPassword(String name, String password);
}