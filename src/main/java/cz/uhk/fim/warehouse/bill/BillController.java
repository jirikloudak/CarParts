package cz.uhk.fim.warehouse.bill;

import cz.uhk.fim.warehouse.unit.UnitEntity;
import cz.uhk.fim.warehouse.unit.UnitServiceImpl;
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
public class BillController {

    private final BillServiceImpl billService;

    private final UnitServiceImpl unitService;

    private static final String VIEW_BILL_FORM = "bills/createOrUpdateBill";

    public BillController(BillServiceImpl billService, UnitServiceImpl unitService) {
        this.billService = billService;
        this.unitService = unitService;
    }

    @GetMapping("/bills")
    public String viewList(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/bills/create")
    public String createForm(@RequestParam Character type, Model model) {
        BillEntity bill = new BillEntity(type);
        String formTitle = null;
        if (type == 'P') {
            formTitle = "Vytvoření příjemky";
        } else if (type == 'V') {
            formTitle = "Vytvoření výdejky";
        }
        List<UnitEntity> units = unitService.getUnitsByType(type);
        model.addAttribute("formTitle", formTitle);
        model.addAttribute("bill", bill);
        model.addAttribute("units", units);
        return VIEW_BILL_FORM;
    }

    @GetMapping("/bills/update/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        BillEntity bill = billService.getBillById(id);
        List<UnitEntity> units = unitService.getUnitsByType(bill.getType());
        model.addAttribute("formTitle", "Editace dokladu " + bill.getType() + bill.getId());
        model.addAttribute("bill", bill);
        model.addAttribute("units", units);
        return VIEW_BILL_FORM;
    }

    @PostMapping("/bills/save")
    public String saveEntity(@Valid BillEntity bill, BindingResult result, Model model) {
        billService.saveBill(bill);
        return "redirect:/bills";
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