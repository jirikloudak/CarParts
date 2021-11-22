package cz.uhk.fim.warehouse.movement;

import cz.uhk.fim.warehouse.bill.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {
    List<MovementEntity> findByBillOrderByIdAsc(BillEntity bill);
}
