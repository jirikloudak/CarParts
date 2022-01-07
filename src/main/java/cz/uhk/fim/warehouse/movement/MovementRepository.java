package cz.uhk.fim.warehouse.movement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {
    Page<MovementEntity> findByBillId(Integer billId, Pageable pageable);
}
