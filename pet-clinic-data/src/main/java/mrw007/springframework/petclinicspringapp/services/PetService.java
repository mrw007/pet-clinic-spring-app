package mrw007.springframework.petclinicspringapp.services;


import mrw007.springframework.petclinicspringapp.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
