package cz.uhk.fim.warehouse.movement;

import org.springframework.data.domain.Page;

public interface MovementService {
    void saveMovement(MovementEntity movement);
    MovementEntity getMovementById(Integer id);
    void deleteMovementById(Integer id);
    Page<MovementEntity> findByBillId(int billId, int pageNo, int pageSize, String sortField, String sortDirection);
    Page<MovementEntity> findByCodeOrName(int billId, String find, int pageNo, int pageSize, String sortField, String sortDirection);
}
