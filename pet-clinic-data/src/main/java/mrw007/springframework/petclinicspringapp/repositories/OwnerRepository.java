package mrw007.springframework.petclinicspringapp.repositories;

import mrw007.springframework.petclinicspringapp.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
