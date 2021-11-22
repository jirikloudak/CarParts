package cz.uhk.fim.warehouse.unit;

import cz.uhk.fim.warehouse.bill.BillEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UnitService {
    void saveUnit(UnitEntity unit);
    UnitEntity getUnitById(Integer id);
    void deleteUnitById(Integer id);
    List<UnitEntity> findByType(Character type);
    Page<UnitEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    Page<UnitEntity> findByName(String name, int pageNo, int pageSize, String sortField, String sortDirection);
}
