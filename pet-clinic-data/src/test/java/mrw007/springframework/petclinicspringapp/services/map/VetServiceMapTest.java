package mrw007.springframework.petclinicspringapp.services.map;

import mrw007.springframework.petclinicspringapp.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetServiceMapTest {

    VetServiceMap vetServiceMap;
    final Long vetId = 1L;

    @BeforeEach
    void setUp() {
        vetServiceMap = new VetServiceMap(new SpecialtyServiceMap());
        vetServiceMap.save(Vet.builder().id(vetId).build());
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = vetServiceMap.findAll();
        assertEquals(1, vetSet.size());
    }

    @Test
    void deleteById() {
        vetServiceMap.deleteById(vetId);
        assertEquals(0, vetServiceMap.findAll().size());
    }

    @Test
    void delete() {
        Vet vet = vetServiceMap.findById(vetId);
        vetServiceMap.delete(vet);
        assertEquals(0, vetServiceMap.findAll().size());
    }

    @Test
    void save() {
        long id = 2L;
        Vet savedVet = vetServiceMap.save(Vet.builder().id(id).build());
        assertNotNull(savedVet);
        assertEquals(id, savedVet.getId());
    }

    @Test
    void findById() {
        Vet vet = vetServiceMap.findById(vetId);
        assertNotNull(vet);
        assertEquals(vetId, vet.getId());
    }
}