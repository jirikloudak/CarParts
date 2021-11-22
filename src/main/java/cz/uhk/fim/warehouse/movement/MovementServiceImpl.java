package cz.uhk.fim.warehouse.movement;

import cz.uhk.fim.warehouse.bill.BillEntity;
import cz.uhk.fim.warehouse.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementServiceImpl implements MovementService{

    private final MovementRepository movementRepository;

    public MovementServiceImpl(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public void saveMovement(MovementEntity movement) {
        movementRepository.save(movement);
    }

    @Override
    public MovementEntity getMovementById(Integer id) {
        return movementRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Movement", "Id", id));
    }

    @Override
    public void deleteMovementById(Integer id) {
        movementRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Movement", "Id", id));
        movementRepository.deleteById(id);
    }

    @Override
    public List<MovementEntity> getMovementsByBill(BillEntity bill) {
        return movementRepository.findByBillOrderByIdAsc(bill);
    }
}
