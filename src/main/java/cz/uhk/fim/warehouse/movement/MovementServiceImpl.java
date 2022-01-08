package cz.uhk.fim.warehouse.movement;

import cz.uhk.fim.warehouse.bill.BillEntity;
import cz.uhk.fim.warehouse.exception.ResourceNotFoundException;
import cz.uhk.fim.warehouse.price.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementServiceImpl implements MovementService{

    @Autowired
    private MovementRepository movementRepository;

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
    public Page<MovementEntity> findByBillId(int billId, int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return movementRepository.findByBillId(billId, pageable);
    }

    @Override
    public Page<MovementEntity> findByCodeOrName(int billId, String find, int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return movementRepository.findByCodeOrName(billId, '%' + find + '%', pageable);
    }
}
