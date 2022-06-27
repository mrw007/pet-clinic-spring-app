package mrw007.springframework.petclinicspringapp.services;

import mrw007.springframework.petclinicspringapp.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();

}
