package mrw007.springframework.petclinicspringapp.services.springDataJPA;

import mrw007.springframework.petclinicspringapp.model.Vet;
import mrw007.springframework.petclinicspringapp.repositories.VetRepository;
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
class VetSDJpaServiceTest {

    public static final long VET_ID = 1L;
    Vet returnVet;

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService service;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().id(VET_ID).build();
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = new HashSet<>();
        vetSet.add(Vet.builder().id(1L).build());
        vetSet.add(Vet.builder().id(2L).build());

        when(vetRepository.findAll()).thenReturn(vetSet);

        Set<Vet> vets = service.findAll();

        assertNotNull(vets);
        assertEquals(2, vets.size());
        verify(vetRepository).findAll();
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));

        Vet vet = service.findById(VET_ID);

        assertNotNull(vet);
        verify(vetRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Vet vet = service.findById(VET_ID);

        assertNull(vet);
        verify(vetRepository).findById(anyLong());
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().id(1L).build();

        when(vetRepository.save(any())).thenReturn(returnVet);

        Vet savedVet = service.save(vetToSave);

        assertNotNull(savedVet);
        verify(vetRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnVet);
        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(VET_ID);
        verify(vetRepository).deleteById(anyLong());
    }
}