package com.bl.addressbook.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bl.addressbook.model.User;

@Repository
public interface AddressBookUserRepo extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM user WHERE mobile_number = ?1", nativeQuery = true)
	User existsByUsermobileNumber(String mobileNumber);

}
