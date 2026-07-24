package org.example.jpa2.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa2.entity.Pet;
import org.example.jpa2.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow();
    }

    public Pet create(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet update(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional // 전후로 스냅샷이 만들어지고 그 스냅샷의 차이를 일괄 반영
    public void deleteById(Long id) {
//        petRepository.deleteById(id);
        Pet pet = findById(id);
        pet.changeDeleted();
//        update(pet);
    }
}
