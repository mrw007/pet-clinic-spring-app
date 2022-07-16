package mrw007.springframework.petclinicspringapp.repositories;

import mrw007.springframework.petclinicspringapp.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
