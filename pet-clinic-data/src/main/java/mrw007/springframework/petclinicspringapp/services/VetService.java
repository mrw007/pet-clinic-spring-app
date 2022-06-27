package mrw007.springframework.petclinicspringapp.services;

import mrw007.springframework.petclinicspringapp.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
