package mrw007.springframework.petclinicspringapp.repositories;

import mrw007.springframework.petclinicspringapp.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
