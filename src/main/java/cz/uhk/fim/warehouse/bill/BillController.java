package cz.uhk.fim.warehouse.bill;

import cz.uhk.fim.warehouse.unit.UnitEntity;
import cz.uhk.fim.warehouse.unit.UnitServiceImpl;
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
import java.time.LocalDate;
import java.util.List;

@Controller
public class BillController {

    @Autowired
    private BillServiceImpl billService;

    @Autowired
    private UnitServiceImpl unitService;

    private static final String VIEW_BILL_FORM = "bills/createOrUpdateBill";

    @GetMapping("/bills")
    public String viewList(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/bills/create")
    public String createForm(@RequestParam Character type, Model model) {
        BillEntity billEntity = new BillEntity(type, LocalDate.now());
        String formTitle = null;
        if (type == 'P') {
            formTitle = "Vytvoření příjemky";
        } else if (type == 'V') {
            formTitle = "Vytvoření výdejky";
        }
        List<UnitEntity> units = unitService.findByType(type);
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("billEntity", billEntity);
        model.addAttribute("units", units);
        return VIEW_BILL_FORM;
    }

    @GetMapping("/bills/update/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        BillEntity billEntity = billService.getBillById(id);
        List<UnitEntity> units = unitService.findByType(billEntity.getType());
        model.addAttribute("formTitle", "Editace dokladu " + billEntity.getType() + billEntity.getId());
        model.addAttribute("billEntity", billEntity);
        model.addAttribute("units", units);
        return VIEW_BILL_FORM;
    }

    @PostMapping("/bills/save")
    public String saveEntity(@Valid BillEntity billEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formTitle;
            if (billEntity.getId() == null && billEntity.getType() == 'P') {
                formTitle = "Vytvoření příjemky";
            } else if (billEntity.getId() == null && billEntity.getType() == 'V') {
                formTitle = "Vytvoření výdejky";
            } else {
                formTitle = "Editace dokladu " + billEntity.getType() + billEntity.getId();
            }
            List<UnitEntity> units = unitService.findByType(billEntity.getType());
            model.addAttribute("formTitle", formTitle);
            model.addAttribute("units", units);
            return VIEW_BILL_FORM;
        } else {
            billService.saveBill(billEntity);
            return "redirect:/bills";
        }
    }

   @GetMapping("/bills/delete/{id}")
    public String deleteEntity(@PathVariable (value = "id") Integer id) {
        billService.deleteBillById(id);
        return "redirect:/bills";
    }

    @GetMapping("/bills/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 9;

        Page<BillEntity> page = billService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<BillEntity> listBills = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listBills", listBills);
        return "/bills/listBills";
    }
}