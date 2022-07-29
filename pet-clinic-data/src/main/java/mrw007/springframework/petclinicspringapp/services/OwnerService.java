package mrw007.springframework.petclinicspringapp.services;

import mrw007.springframework.petclinicspringapp.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

}
