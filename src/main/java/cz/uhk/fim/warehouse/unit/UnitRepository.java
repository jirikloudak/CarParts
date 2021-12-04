package cz.uhk.fim.warehouse.unit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Integer> {
    List<UnitEntity> findByTypeOrderByName(Character type);
    Page<UnitEntity> findByNameContaining(String name, Pageable pageable);
}
