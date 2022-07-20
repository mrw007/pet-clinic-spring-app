package mrw007.springframework.petclinicspringapp.services.springDataJPA;

import mrw007.springframework.petclinicspringapp.model.PetType;
import mrw007.springframework.petclinicspringapp.repositories.PetTypeRepository;
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
class PetTypeSDJpaServiceTest {

    public static final long PET_TYPE_ID = 1L;
    PetType returnPetType;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService service;

    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(PET_TYPE_ID).build();
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = new HashSet<>();
        petTypeSet.add(PetType.builder().id(1L).build());
        petTypeSet.add(PetType.builder().id(2L).build());

        when(petTypeRepository.findAll()).thenReturn(petTypeSet);

        Set<PetType> petTypes = service.findAll();

        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));

        PetType petType = service.findById(PET_TYPE_ID);

        assertNotNull(petType);
        verify(petTypeRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        PetType petType = service.findById(PET_TYPE_ID);

        assertNull(petType);
        verify(petTypeRepository).findById(anyLong());
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().id(1L).build();

        when(petTypeRepository.save(any())).thenReturn(returnPetType);

        PetType savedPetType = service.save(petTypeToSave);

        assertNotNull(savedPetType);
        verify(petTypeRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnPetType);
        verify(petTypeRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(PET_TYPE_ID);
        verify(petTypeRepository).deleteById(anyLong());
    }
}