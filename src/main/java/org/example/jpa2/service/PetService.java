package org.example.jpa2.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa2.entity.Animal;
import org.example.jpa2.entity.Pet;
import org.example.jpa2.entity.PetHistory;
import org.example.jpa2.repository.AnimalRepository;
import org.example.jpa2.repository.PetHistoryRepository;
import org.example.jpa2.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final PetHistoryRepository petHistoryRepository;
    private final AnimalRepository animalRepository;

    public List<Pet> findAll() {
//        return petRepository.findAll();
//        return petRepository.findAllByIsDeletedFalse();
        return petRepository.findAllDesc();
    }

    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow();
    }

    public Pet create(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional
    public Pet update(Pet pet) {
        Optional<PetHistory> history = petHistoryRepository.findByPet(pet);
        if (history.isEmpty()) {
            PetHistory petHistory = PetHistory.builder()
                    .pet(pet)
                    .count(1)
                    .build();
            petHistoryRepository.save(petHistory);
        } else {
            history.get().increment();
        }
        return petRepository.save(pet);
        // transaction을 활용한 더티 체킹이 아닌 경우엔
        // save로 update, insert도 쓴다
        // -> id가 있어야함
    }

    @Transactional // 전후로 스냅샷이 만들어지고 그 스냅샷의 차이를 일괄 반영
    public void deleteById(Long id) {
//        petRepository.deleteById(id);
        Pet pet = findById(id);
        pet.changeDeleted();
//        update(pet);
    }

    public List<Animal> findAllAnimal() {
        return animalRepository.findAll();
    }

    public Animal findAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow();
    }

    public void createAnimal(Animal animal) {
        animalRepository.save(animal);
    }
}
