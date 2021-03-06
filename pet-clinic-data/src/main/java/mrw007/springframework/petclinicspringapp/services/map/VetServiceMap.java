package mrw007.springframework.petclinicspringapp.services.map;

import mrw007.springframework.petclinicspringapp.model.Specialty;
import mrw007.springframework.petclinicspringapp.model.Vet;
import mrw007.springframework.petclinicspringapp.services.SpecialtyService;
import mrw007.springframework.petclinicspringapp.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            if (object.getSpecialities() != null && object.getSpecialities().size() > 0) {
                object.getSpecialities().forEach(specialty -> {
                    if (specialty.getId() == null) {
                        Specialty savedSpecialty = specialtyService.save(specialty);
                        specialty.setId(savedSpecialty.getId());
                    }
                });
            }
            return super.save(object);
        }
        return null;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
