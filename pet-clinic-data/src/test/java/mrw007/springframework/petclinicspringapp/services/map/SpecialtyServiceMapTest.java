package mrw007.springframework.petclinicspringapp.services.map;

import mrw007.springframework.petclinicspringapp.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SpecialtyServiceMapTest {

    SpecialtyServiceMap specialtyServiceMap;
    final Long specialtyId = 1L;
    final String specialtyDescription = "Radiology";

    @BeforeEach
    void setUp() {
        specialtyServiceMap = new SpecialtyServiceMap();
        specialtyServiceMap.save(Specialty.builder().id(specialtyId).description(specialtyDescription).build());
    }

    @Test
    void findAll() {
        Set<Specialty> specialtySet = specialtyServiceMap.findAll();
        assertEquals(1, specialtySet.size());
    }

    @Test
    void deleteById() {
        specialtyServiceMap.deleteById(specialtyId);
        assertEquals(0, specialtyServiceMap.findAll().size());
    }

    @Test
    void delete() {
        Specialty specialty = specialtyServiceMap.findById(specialtyId);
        specialtyServiceMap.delete(specialty);
        assertEquals(0, specialtyServiceMap.findAll().size());
    }

    @Test
    void save() {
        long id = 2L;
        Specialty specialty2 = specialtyServiceMap.save(Specialty.builder().id(id).build());
        assertNotNull(specialty2);
        assertEquals(id, specialty2.getId());
    }

    @Test
    void findById() {
        Specialty specialty = specialtyServiceMap.findById(specialtyId);
        assertNotNull(specialty);
        assertEquals(specialtyId, specialty.getId());
    }
}