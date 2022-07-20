package mrw007.springframework.petclinicspringapp.services.springDataJPA;

import mrw007.springframework.petclinicspringapp.model.Visit;
import mrw007.springframework.petclinicspringapp.repositories.VisitRepository;
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
class VisitSDJpaServiceTest {

    public static final long VISIT_ID = 1L;
    Visit returnVisit;

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @BeforeEach
    void setUp() {
        returnVisit = Visit.builder().id(VISIT_ID).build();
    }

    @Test
    void findAll() {
        Set<Visit> visitSet = new HashSet<>();
        visitSet.add(Visit.builder().id(1L).build());
        visitSet.add(Visit.builder().id(2L).build());

        when(visitRepository.findAll()).thenReturn(visitSet);

        Set<Visit> visits = service.findAll();

        assertNotNull(visits);
        assertEquals(2, visits.size());
        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));

        Visit visit = service.findById(VISIT_ID);

        assertNotNull(visit);
        verify(visitRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());

        Visit visit = service.findById(VISIT_ID);

        assertNull(visit);
        verify(visitRepository).findById(anyLong());
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().id(1L).build();

        when(visitRepository.save(any())).thenReturn(returnVisit);

        Visit savedVisit = service.save(visitToSave);

        assertNotNull(savedVisit);
        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnVisit);
        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(VISIT_ID);
        verify(visitRepository).deleteById(anyLong());
    }
}