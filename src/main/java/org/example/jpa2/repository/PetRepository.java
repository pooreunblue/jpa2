package org.example.jpa2.repository;

import org.example.jpa2.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

//    public List<Pet>
//    findAll/By/IsDeleted/False/(); - Hibernate가 실제 작성해줌
//    public List<Pet> findAllByIsDeletedFalse();
    @Query("""
    SELECT p
        FROM Pet p
        WHERE p.isDeleted = false
        ORDER BY p.id DESC
""")
    List<Pet> findAllDesc();
}
