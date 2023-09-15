package com.example.msusers.service;

import com.example.msusers.domain.Bill;
import com.example.msusers.domain.User;
import com.example.msusers.repository.BillRepository;
import com.example.msusers.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
   private final UserRepository userRepository;
   private final BillRepository billRepository;

    public UserService(UserRepository userRepository, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        user.setBills(getBillsByUserId(user.getId()));
        return user;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        user.setBills(getBillsByUserId(user.getId()));
        return user;
    }

    private List<Bill> getBillsByUserId(String userId) {
        return billRepository.findBillsByUserId(userId);
    }

}

