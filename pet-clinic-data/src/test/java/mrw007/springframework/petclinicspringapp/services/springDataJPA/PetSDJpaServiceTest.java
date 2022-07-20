package mrw007.springframework.petclinicspringapp.services.springDataJPA;

import mrw007.springframework.petclinicspringapp.model.Pet;
import mrw007.springframework.petclinicspringapp.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    public static final long PET_ID = 1L;
    Pet returnPet;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService service;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(PET_ID).build();
    }

    @Test
    void findAll() {
        Set<Pet> petSet = new HashSet<>();
        petSet.add(Pet.builder().id(1L).build());
        petSet.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(petSet);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet = service.findById(PET_ID);

        assertNotNull(pet);
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet pet = service.findById(PET_ID);

        assertNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().id(1L).build();

        when(petRepository.save(any())).thenReturn(returnPet);

        Pet savedPet = service.save(petToSave);

        assertNotNull(savedPet);
        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPet);
        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(PET_ID);
        verify(petRepository).deleteById(anyLong());
    }
}