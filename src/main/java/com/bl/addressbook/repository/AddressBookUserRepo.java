package com.bl.addressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.addressbook.model.User;

@Repository
public interface AddressBookUserRepo extends JpaRepository<User, Long>{

}
