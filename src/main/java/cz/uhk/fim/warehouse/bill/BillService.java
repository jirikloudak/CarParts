package cz.uhk.fim.warehouse.bill;

import cz.uhk.fim.warehouse.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements BillServiceInt{

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<BillEntity> getAllBills(){
        return billRepository.findAll();
    };

    @Override
    public void saveBill(BillEntity bill){
        billRepository.save(bill);
    };

    @Override
    public BillEntity getBillById(Integer id){
        return billRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Bill", "Id", id));
    };

    @Override
    public void deleteBillById(Integer id){
        billRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Bill", "Id", id));
        billRepository.deleteById(id);
    };

    @Override
    public Page<BillEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.billRepository.findAll(pageable);
    };
}
