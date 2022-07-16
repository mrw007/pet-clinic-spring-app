package mrw007.springframework.petclinicspringapp.repositories;

import mrw007.springframework.petclinicspringapp.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
