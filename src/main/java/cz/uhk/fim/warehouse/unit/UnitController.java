package cz.uhk.fim.warehouse.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UnitController {

    @Autowired
    private UnitServiceImpl unitService;

    private static final String VIEW_UNIT_FORM = "units/createOrUpdateUnit";

    @GetMapping("/units")
    public String viewList (Model model) {return findPaginated(1,"id", "asc", null, model);}

    @GetMapping("/units/delete/{id}")
    public String deleteEntity(@PathVariable(value = "id") Integer id) {
        unitService.deleteUnitById(id);
        return "redirect:/units";
    }

    @GetMapping("/units/page/{pageNo}")
    public String findPaginated(@PathVariable(name = "pageNo") int pageNo,
                                @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                                @RequestParam(name = "search", required = false) String search,
                                Model model) {
        int pageSize = 9;
        Page<UnitEntity> page;
        if (search != null && !search.isBlank())
        {
            page = unitService.findByName(search, pageNo, pageSize, sortField, sortDir);
        } else {
            page = unitService.findPaginated(pageNo, pageSize, sortField, sortDir);
        }
        List<UnitEntity> listUnits = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("search", search);
        model.addAttribute("listUnits", listUnits);
        return "/units/listUnits";
    }

    @GetMapping("/units/update/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        UnitEntity unitEntity = unitService.getUnitById(id);
        model.addAttribute("formTitle", "Editace Střediska " + unitEntity.getType() + unitEntity.getId());
        model.addAttribute("unitEntity", unitEntity);
        return VIEW_UNIT_FORM;
    }

    @GetMapping("/units/create")
    public String createForm(@RequestParam Character type, Model model) {
        UnitEntity unitEntity = new UnitEntity(type);
        String formTitle = null;
        if (type == 'P') {
            formTitle = "Vytvoření dodavatele";
        } else if (type == 'V') {
            formTitle = "Vytvoření odběratele";
        }
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("unitEntity", unitEntity);
        return VIEW_UNIT_FORM;
    }

    @PostMapping("/units/save")
    public String saveEntity(@Valid UnitEntity unitEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formTitle;
            if (unitEntity.getId() == null && unitEntity.getType() == 'P') {
                formTitle = "Vytvoření dodavatele";
            } else if (unitEntity.getId() == null && unitEntity.getType() == 'V') {
                formTitle = "Vytvoření odběratele";
            } else {
                formTitle = "Editace střediska " + unitEntity.getType() + unitEntity.getId();
            }
            model.addAttribute("formTitle", formTitle);
            return VIEW_UNIT_FORM;
        } else {
            unitService.saveUnit(unitEntity);
            return "redirect:/units";
        }
    }
}
