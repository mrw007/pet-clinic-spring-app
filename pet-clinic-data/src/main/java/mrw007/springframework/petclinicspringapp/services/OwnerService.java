package mrw007.springframework.petclinicspringapp.services;

import mrw007.springframework.petclinicspringapp.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
