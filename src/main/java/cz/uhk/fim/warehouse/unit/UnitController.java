package cz.uhk.fim.warehouse.unit;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UnitController {
    private final UnitServiceImpl unitService;

    public UnitController(UnitServiceImpl unitService) {
        this.unitService = unitService;
    }

    @GetMapping("/units")
    public String viewList (Model model) {return findPaginated(1,"id", "asc", model);}

    @GetMapping("/units/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 9;

        Page<UnitEntity> page = unitService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<UnitEntity> listUnits = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUnits", listUnits);
        return "/units/listUnits";
    }
}
