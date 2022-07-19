package mrw007.springframework.petclinicspringapp.services.map;

import mrw007.springframework.petclinicspringapp.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {

    PetServiceMap petServiceMap;
    final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        petServiceMap.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petServiceMap.findAll();
        assertEquals(1, petSet.size());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(petId);
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void delete() {
        Pet pet = petServiceMap.findById(petId);
        petServiceMap.delete(pet);
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void save() {
        long id = 2L;
        Pet savedPet = petServiceMap.save(Pet.builder().id(id).build());
        assertNotNull(savedPet);
        assertEquals(id, savedPet.getId());
    }

    @Test
    void findById() {
        Pet pet = petServiceMap.findById(petId);
        assertNotNull(pet);
        assertEquals(petId, pet.getId());
    }
}