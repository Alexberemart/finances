package com.alexberemart.finances.infraestructure.out.adapters;

import com.alexberemart.finances.domain.models.BankAccount;
import com.alexberemart.finances.domain.ports.repositories.BankAccountRepository; // Domain port
import com.alexberemart.finances.infraestructure.out.entities.BankAccountEntity;
import com.alexberemart.finances.infraestructure.out.repositories.JpaBankAccountRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankAccountRepositoryAdapter implements BankAccountRepository {

    private final com.alexberemart.finances.infraestructure.out.repositories.JpaBankAccountRepository repository;

    public BankAccountRepositoryAdapter(JpaBankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BankAccount> findById(String id) {
        return repository.findById(id)
            .map(this::toDomain);
    }

    private BankAccount toDomain(BankAccountEntity entity) {
        BankAccount domain = new BankAccount();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        return domain;
    }
}