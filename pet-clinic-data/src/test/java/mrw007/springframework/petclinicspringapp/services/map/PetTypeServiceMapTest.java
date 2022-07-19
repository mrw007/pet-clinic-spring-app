package mrw007.springframework.petclinicspringapp.services.map;

import mrw007.springframework.petclinicspringapp.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeServiceMapTest {

    PetTypeServiceMap petTypeServiceMap;
    final Long petTypeId = 1L;
    final String petTypeName = "cat";

    @BeforeEach
    void setUp() {
        petTypeServiceMap = new PetTypeServiceMap();
        petTypeServiceMap.save(PetType.builder().id(petTypeId).name(petTypeName).build());
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = petTypeServiceMap.findAll();
        assertEquals(1, petTypeSet.size());
    }

    @Test
    void deleteById() {
        petTypeServiceMap.deleteById(petTypeId);
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void delete() {
        PetType petType = petTypeServiceMap.findById(petTypeId);
        petTypeServiceMap.delete(petType);
        assertEquals(0, petTypeServiceMap.findAll().size());
    }

    @Test
    void save() {
        long id = 2L;
        PetType savedPetType = petTypeServiceMap.save(PetType.builder().id(id).build());
        assertNotNull(savedPetType);
        assertEquals(id, savedPetType.getId());
    }

    @Test
    void findById() {
        PetType petType = petTypeServiceMap.findById(petTypeId);
        assertNotNull(petType);
        assertEquals(petTypeId, petType.getId());
    }
}