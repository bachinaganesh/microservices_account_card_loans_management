package com.ganesh.cards.repository;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.ganesh.cards.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

    public Optional<Card> findByMobileNumber(String mobileNumber);

    @Modifying
    @Transactional
    public void deleteByMobileNumber(String mobileNumber);
}
