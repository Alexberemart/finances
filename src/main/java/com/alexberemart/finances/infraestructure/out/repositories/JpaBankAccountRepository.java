package com.alexberemart.finances.infraestructure.out.repositories;

import com.alexberemart.finances.infraestructure.out.entities.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBankAccountRepository extends JpaRepository<BankAccountEntity, String> {
    // You can add custom query methods here if needed
}