package com.example.msusers.repository;

import com.example.msusers.domain.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepository {

    private final FeignBillRepository feignBillRepository;

    public BillRepository(FeignBillRepository feignBillRepository) {
        this.feignBillRepository = feignBillRepository;
    }

    public List<Bill> findBillsByUserId(String userId) {
        return feignBillRepository.findBillsByUserId(userId).getBody();
    }

}
