package cz.uhk.fim.warehouse.part;

import cz.uhk.fim.warehouse.price.PriceEntity;
import cz.uhk.fim.warehouse.price.PriceServiceImpl;
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
import java.util.Optional;

@Controller
class PartController {

    @Autowired
    private PartServiceImpl partService;

    @Autowired
    private PriceServiceImpl priceService;

    private static final String VIEW_PART_FORM = "parts/createOrUpdatePart";

    @GetMapping("/")
    public String viewList(Model model) {
        return findPaginated(1, "id", "asc", null, false, model);
    }

    @GetMapping("/parts/delete/{id}")
    public String deleteEntity(@PathVariable(value = "id") Integer id) {
        partService.deletePart(id);
        return "redirect:/";
    }

    @GetMapping("/parts/page/{pageNo}")
    public String findPaginated(@PathVariable(name = "pageNo") int pageNo,
                                @RequestParam(name = "sortField", defaultValue = "id") String sortField,
                                @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                                @RequestParam(name = "search", required = false) String search,
                                @RequestParam(name = "order", required = false) boolean order,
                                Model model) {
        int pageSize = 9;

        Page<PartEntity> page;
        if (search != null)
        {
            page = partService.findByCodeOrName(search, pageNo, pageSize, sortField, sortDir);
        } else if (order) {
            page = partService.findPartsToOrder(pageNo, pageSize, sortField, sortDir);
        } else {
            page = partService.findPaginated(pageNo, pageSize, sortField, sortDir);
        }
        List<PartEntity> listParts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("search", search);
        model.addAttribute("order", order);
        model.addAttribute("listParts", listParts);
        return "/parts/listParts";
    }

    @GetMapping("/parts/update/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        PartEntity partEntity = partService.getPartById(id);
        List<PriceEntity> prices = priceService.findAllPrices();
        model.addAttribute("formTitle", "Editace dílu " + partEntity.getName() + " - Id: " + partEntity.getId());
        model.addAttribute("partEntity", partEntity);
        model.addAttribute("prices", prices);
        return VIEW_PART_FORM;
    }

    @PostMapping("/parts/save")
    public String saveEntity(@Valid PartEntity partEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formTitle;
            formTitle = "Editace dokladu " + partEntity.getName() + partEntity.getId();
            List<PriceEntity> prices = priceService.findAllPrices();
            model.addAttribute("formTitle", formTitle);
            model.addAttribute("prices", prices);
            return VIEW_PART_FORM;
        } else {
            partService.savePart(partEntity);
            return "redirect:/";
        }
    }

    @GetMapping("/parts/create")
    public String createForm(Model model) {
        PartEntity partEntity = new PartEntity();
        String formTitle = "Vytvoření nového dílu";
        List<PriceEntity> prices = priceService.findAllPrices();
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("partEntity", partEntity);
        model.addAttribute("prices", prices);
        return VIEW_PART_FORM;
    }
}
