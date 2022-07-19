package mrw007.springframework.petclinicspringapp.services.map;

import mrw007.springframework.petclinicspringapp.model.Owner;
import mrw007.springframework.petclinicspringapp.model.Pet;
import mrw007.springframework.petclinicspringapp.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitServiceMapTest {

    VisitServiceMap visitServiceMap;
    final Owner owner = Owner.builder().id(1L).build();
    final Pet pet = Pet.builder().id(1L).owner(owner).build();
    final Long visitId = 1L;

    @BeforeEach
    void setUp() {
        visitServiceMap = new VisitServiceMap();
        visitServiceMap.save(Visit.builder().id(visitId).pet(pet).build());
    }

    @Test
    void findAll() {
        Set<Visit> visitSet = visitServiceMap.findAll();
        assertEquals(1, visitSet.size());
    }

    @Test
    void deleteById() {
        visitServiceMap.deleteById(visitId);
        assertEquals(0, visitServiceMap.findAll().size());
    }

    @Test
    void delete() {
        Visit visit = visitServiceMap.findById(visitId);
        visitServiceMap.delete(visit);
        assertEquals(0, visitServiceMap.findAll().size());
    }

    @Test
    void save() {
        long id = 2L;
        Visit savedVisit = visitServiceMap.save(Visit.builder().id(id).pet(pet).build());
        assertNotNull(savedVisit);
        assertEquals(id, savedVisit.getId());
    }

    @Test
    void saveFail() {
        long id = 3L;
        final String expectedException = "Invalid Visit";
        Exception exception = assertThrows(RuntimeException.class, () -> visitServiceMap.save(Visit.builder().id(id).build()));
        assertTrue(expectedException.contains(exception.getMessage()));
    }

    @Test
    void findById() {
        Visit visit = visitServiceMap.findById(visitId);
        assertNotNull(visit);
        assertEquals(visitId, visit.getId());
    }
}