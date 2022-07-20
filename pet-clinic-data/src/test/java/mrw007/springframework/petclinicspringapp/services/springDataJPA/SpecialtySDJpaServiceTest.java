package mrw007.springframework.petclinicspringapp.services.springDataJPA;

import mrw007.springframework.petclinicspringapp.model.Specialty;
import mrw007.springframework.petclinicspringapp.repositories.SpecialtyRepository;
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
class SpecialtySDJpaServiceTest {

    public static final long SPECIALTY_ID = 1L;
    Specialty returnSpecialty;

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialtySDJpaService service;

    @BeforeEach
    void setUp() {
        returnSpecialty = Specialty.builder().id(SPECIALTY_ID).build();
    }

    @Test
    void findAll() {
        Set<Specialty> specialtySet = new HashSet<>();
        specialtySet.add(Specialty.builder().id(1L).build());
        specialtySet.add(Specialty.builder().id(2L).build());

        when(specialtyRepository.findAll()).thenReturn(specialtySet);

        Set<Specialty> specialties = service.findAll();

        assertNotNull(specialties);
        assertEquals(2, specialties.size());
        verify(specialtyRepository).findAll();
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(returnSpecialty));

        Specialty specialty = service.findById(SPECIALTY_ID);

        assertNotNull(specialty);
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());

        Specialty specialty = service.findById(SPECIALTY_ID);

        assertNull(specialty);
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void save() {
        Specialty specialtyToSave = Specialty.builder().id(1L).build();

        when(specialtyRepository.save(any())).thenReturn(returnSpecialty);

        Specialty savedSpecialty = service.save(specialtyToSave);

        assertNotNull(savedSpecialty);
        verify(specialtyRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnSpecialty);
        verify(specialtyRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(SPECIALTY_ID);
        verify(specialtyRepository).deleteById(anyLong());
    }
}