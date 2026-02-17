package com.ganesh.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.ganesh.loans.model.Loan;

import jakarta.transaction.Transactional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
    public Optional<Loan> findByMobileNumber(String mobileNumber);
    @Modifying
    @Transactional
    public void deleteByMobileNumber(String mobileNumber);
}
