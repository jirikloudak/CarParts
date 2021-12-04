package cz.uhk.fim.warehouse.part;

import cz.uhk.fim.warehouse.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements PartService{

    @Autowired
    private PartRepository partRepository;

    @Override
    public void savePart(PartEntity part) {
        partRepository.save(part);
    }

    @Override
    public PartEntity getPartById(Integer id) {
        return partRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Part", "Id", id));
    }

    @Override
    public void deletePart(Integer id) {
        partRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Part", "Id", id));
        partRepository.deleteById(id);
    }

    @Override
    public List<PartEntity> findAllParts() {
        return partRepository.findAllByOrderByCodeAsc();
    }

    @Override
    public Page<PartEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return partRepository.findAll(pageable);
    }

    @Override
    public Page<PartEntity> findPartsToOrder(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return partRepository.findPartsToOrder(pageable);
    }

    @Override
    public Page<PartEntity> findByCodeOrName(String find, int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return partRepository.findByCodeOrName('%' + find + '%', pageable);
    }
}
