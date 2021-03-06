package com.rootuss.BloodDonationCentre.donation.repository;

import com.rootuss.BloodDonationCentre.blood.model.Blood;
import com.rootuss.BloodDonationCentre.donation.model.Donation;
import com.rootuss.BloodDonationCentre.donation.model.EDonationType;
import com.rootuss.BloodDonationCentre.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Override
    List<Donation> findAll();

    @Query("SELECT d FROM Donation d WHERE d.user = :user ORDER BY d.date DESC")
    List<Donation> findByUser(User user);

    @Query("SELECT d FROM Donation d WHERE d.user.id = :userId ORDER BY d.date DESC")
    List<Donation> findByAllUserId(Long userId);

    @Query("SELECT distinct d FROM Donation d JOIN d.user u WHERE (:bloodGroupWithRh is null or u.blood = :bloodGroupWithRh) " +
            "AND (:eDonationType is null or d.donationType = :eDonationType) AND (:isReleased is null or d.isReleased = :isReleased)")
    List<Donation> findAllByDonationTypeAndIsReleasedAndBloodGroupWithRh(
            EDonationType eDonationType, Boolean isReleased, Blood bloodGroupWithRh);
}
