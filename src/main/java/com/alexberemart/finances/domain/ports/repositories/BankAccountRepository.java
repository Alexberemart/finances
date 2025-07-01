package com.alexberemart.finances.domain.ports.repositories;

import com.alexberemart.finances.domain.models.BankAccount;
import java.util.Optional;

public interface BankAccountRepository {
    Optional<BankAccount> findById(String id);
}