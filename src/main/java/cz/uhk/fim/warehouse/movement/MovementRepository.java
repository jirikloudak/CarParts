package cz.uhk.fim.warehouse.movement;

import cz.uhk.fim.warehouse.bill.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {
    List<MovementEntity> findByBillOrderByIdAsc(BillEntity bill);
}
