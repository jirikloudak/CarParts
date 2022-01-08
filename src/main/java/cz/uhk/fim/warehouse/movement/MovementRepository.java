package cz.uhk.fim.warehouse.movement;

import cz.uhk.fim.warehouse.part.PartEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {
    Page<MovementEntity> findByBillId(Integer billId, Pageable pageable);

    @Query("SELECT m FROM MovementEntity m WHERE m.bill.id = :billId AND m.part.code LIKE :find OR m.part.name LIKE :find")
    @Transactional(readOnly = true)
    Page<MovementEntity> findByCodeOrName(Integer billId, String find, Pageable pageable);
}
