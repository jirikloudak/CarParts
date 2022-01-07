package cz.uhk.fim.warehouse.movement;

import cz.uhk.fim.warehouse.bill.BillEntity;
import cz.uhk.fim.warehouse.bill.BillServiceImpl;
import cz.uhk.fim.warehouse.part.PartEntity;
import cz.uhk.fim.warehouse.part.PartServiceImpl;
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
public class MovementController {

    @Autowired
    private BillServiceImpl billService;

    @Autowired
    private MovementServiceImpl movementService;

    @Autowired
    private PartServiceImpl partService;

    private static final String VIEW_MOVEMENT_FORM = "movements/createOrUpdateMovement";

    @GetMapping("/movements/{billId}")
    public String viewList(@PathVariable(value = "billId") int billId, Model model) {
        return findPaginated(billId, 1, "part", "asc", model);
    }

    @GetMapping("/movements/{billId}/delete/{id}")
    public String deleteEntity(@PathVariable(value = "billId") int billId,
                               @PathVariable(value = "id") Integer id) {
        movementService.deleteMovementById(id);
        return "redirect:/movements/" + billId;
    }

    @GetMapping("/movements/{billId}/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "billId") int billId,
                                @PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 9;

        Page<MovementEntity> page = movementService.findByBillId(billId, pageNo, pageSize, sortField, sortDir);
        List<MovementEntity> listMovements = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("bill", billId);
        model.addAttribute("listMovements", listMovements);
        return "movements/listMovements";
    }

    @GetMapping("/movements/update/{id}")
    public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
        MovementEntity movementEntity = movementService.getMovementById(id);
        List<PartEntity> parts = partService.findAllParts();
        model.addAttribute("formTitle", "Editace pohybu pro doklad: " +
                movementEntity.getBill().getType() + movementEntity.getBill().getId());
        model.addAttribute("movementEntity", movementEntity);
        model.addAttribute("parts", parts);
        return VIEW_MOVEMENT_FORM;
    }

    @PostMapping("/movements/save")
    public String saveEntity(@Valid MovementEntity movementEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            String formTitle;
            if (movementEntity.getId() == null) {
                formTitle = "Vytvoření pohybu pro doklad: " +
                        movementEntity.getBill().getType() + movementEntity.getBill().getId();
            } else {
                formTitle = "Editace pohybu pro doklad: " +
                        movementEntity.getBill().getType() + movementEntity.getBill().getId();
            }
            model.addAttribute("formTitle", formTitle);
            model.addAttribute("formTitle", "Editace pohybu pro doklad: " +
                    movementEntity.getBill().getType() + movementEntity.getBill().getId());
            return VIEW_MOVEMENT_FORM;
        } else {
            movementService.saveMovement(movementEntity);
            return "redirect:/movements/" + movementEntity.getBill().getId();
        }
    }

    @GetMapping("/movements/create/{billId}")
    public String createForm(@PathVariable(value = "billId") Integer billId,Model model) {
        BillEntity bill = billService.getBillById(billId);
        MovementEntity movementEntity = new MovementEntity();
        movementEntity.setBill(bill);
        List<PartEntity> parts = partService.findAllParts();
        model.addAttribute("formTitle", "Vytvoření pohybu pro doklad: " +
                bill.getType() + bill.getId());
        model.addAttribute("movementEntity", movementEntity);
        model.addAttribute("parts", parts);
        return VIEW_MOVEMENT_FORM;
    }
}
