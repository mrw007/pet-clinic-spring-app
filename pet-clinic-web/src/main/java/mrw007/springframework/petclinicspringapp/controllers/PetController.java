package mrw007.springframework.petclinicspringapp.controllers;

import mrw007.springframework.petclinicspringapp.model.Owner;
import mrw007.springframework.petclinicspringapp.model.Pet;
import mrw007.springframework.petclinicspringapp.model.PetType;
import mrw007.springframework.petclinicspringapp.services.OwnerService;
import mrw007.springframework.petclinicspringapp.services.PetService;
import mrw007.springframework.petclinicspringapp.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    public static final String VIEWS_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";
    public static final String VIEWS_REDIRECT_OWNERS = "redirect:/owners/";
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = Pet.builder().build();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult result, Model model) {
        if (StringUtils.hasLength(pet.getName())
                && pet.isNew()
                && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_CREATE_OR_UPDATE_PET_FORM;
        } else {
            petService.save(pet);
            return VIEWS_REDIRECT_OWNERS + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_CREATE_OR_UPDATE_PET_FORM;
        } else {
            owner.getPets().add(pet);
            petService.save(pet);
            return VIEWS_REDIRECT_OWNERS + owner.getId();
        }
    }
}
