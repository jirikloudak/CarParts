package cz.uhk.fim.warehouse.part;

import org.springframework.data.domain.Page;

import java.util.List;

public interface PartService {
    void savePart(PartEntity part);
    PartEntity getPartById(Integer id);
    void deletePart(Integer id);
    List<PartEntity> findAllParts();
    Page<PartEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    Page<PartEntity> findPartsToOrder(int pageNo, int pageSize, String sortField, String sortDirection);
    Page<PartEntity> findByCodeOrName(String find, int pageNo, int pageSize, String sortField, String sortDirection);
}
