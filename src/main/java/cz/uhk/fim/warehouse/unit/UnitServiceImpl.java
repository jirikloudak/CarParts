package cz.uhk.fim.warehouse.unit;

import cz.uhk.fim.warehouse.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public void saveUnit(UnitEntity unit) {
        unitRepository.save(unit);
    }

    @Override
    public UnitEntity getUnitById(Integer id) {
        return unitRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unit", "Id", id));
    }

    @Override
    public void deleteUnitById(Integer id) {
        unitRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unit", "Id", id));
        unitRepository.deleteById(id);
    }

    @Override
    public List<UnitEntity> findByType(Character type) {
        return unitRepository.findByTypeOrderByName(type);
    }

    @Override
    public Page<UnitEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return unitRepository.findAll(pageable);
    }

    @Override
    public Page<UnitEntity> findByName(String name, int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return unitRepository.findByNameContaining(name, pageable);
    }
}
