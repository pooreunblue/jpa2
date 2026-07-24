package org.example.jpa2.repository;

import org.example.jpa2.entity.Doctor;
import org.example.jpa2.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("""
SELECT r
FROM Reservation r
JOIN FETCH r.doctor
JOIN FETCH r.pet
WHERE r.doctor.id = :doctorId
""")
// LEFT JOIN을 쓸 경우에는 Reservation r -> Lazy
// Fetch JOIN
    List<Reservation> findDoctorReservation(Long doctorId);
}
