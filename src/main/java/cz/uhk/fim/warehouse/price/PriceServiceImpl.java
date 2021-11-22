package cz.uhk.fim.warehouse.price;

import cz.uhk.fim.warehouse.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public void savePrice(PriceEntity price) {
        priceRepository.save(price);
    }

    @Override
    public PriceEntity getPriceById(Integer id) {
        return priceRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Price", "Id", id));
    }

    @Override
    public void deletePriceById(Integer id) {
        priceRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Price", "Id", id));
        priceRepository.deleteById(id);
    }

    @Override
    public List<PriceEntity> findAllPrices() {
        return priceRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Page<PriceEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return priceRepository.findAllByOrderByNameAsc(pageable);
    }

    @Override
    public Page<PriceEntity> findByName(String name, int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return priceRepository.findByNameContainingOrderByName(name, pageable);
    }
}