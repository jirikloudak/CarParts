package cz.uhk.fim.warehouse.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UnitRepository extends JpaRepository<UnitEntity, Integer> {

    @Query("SELECT u FROM UnitEntity u WHERE u.type = :type")
    List<UnitEntity> findUnitsByType(String type);
}
