package cz.uhk.fim.warehouse.unit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitEntity, Integer> {
    List<UnitEntity> findByTypeOrderByName(String type);
    Page<UnitEntity> findAllByOrderByTypeAscNameAsc(Pageable pageable);
    Page<UnitEntity> findByNameContainingOrderByTypeAscNameAsc(String name, Pageable pageable);
}
