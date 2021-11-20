package cz.uhk.fim.warehouse.unit;

import java.util.List;

public interface UnitService {
    List<UnitEntity> getAllUnits();
    List<UnitEntity> getUnitsByType(Character type);
}
