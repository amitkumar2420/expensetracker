package com.account.repo;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.account.model.Account;


@Repository
public interface AccountRepo extends MongoRepository<Account, Integer>{

	Account findByAccountId(int accountId);

	
	Account findByUsername(String username);

}
