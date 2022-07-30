package mrw007.springframework.petclinicspringapp.controllers;

import mrw007.springframework.petclinicspringapp.model.Owner;
import mrw007.springframework.petclinicspringapp.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    public static final String CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    public static final String FIND_OWNERS = "owners/findOwners";
    public static final String OWNER_DETAILS = "owners/ownerDetails";
    public static final String OWNERS_LIST = "owners/ownersList";
    public static final String REDIRECT_OWNERS = "redirect:/owners/";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return FIND_OWNERS;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        //allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadcast possible search
        }
        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if (results.isEmpty()) {
            //no owners found
            result.rejectValue("lastName", "not found", "not found");
            return FIND_OWNERS;
        } else if (results.size() == 1) {
            //1 owner found
            owner = results.get(0);
            return REDIRECT_OWNERS + owner.getId();
        } else {
            //multiple owners found
            model.addAttribute("selections", results);
            return OWNERS_LIST;
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView(OWNER_DETAILS);
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Validated Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return REDIRECT_OWNERS + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Validated Owner owner, BindingResult result,
                                         @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return REDIRECT_OWNERS + savedOwner.getId();
        }
    }
}
