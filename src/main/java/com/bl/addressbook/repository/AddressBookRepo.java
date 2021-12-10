package com.bl.addressbook.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bl.addressbook.model.User;

@Repository
public interface AddressBookRepo extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM user WHERE mobile_number = ?1", nativeQuery = true)
	User existsByUsermobileNumber(String mobileNumber);
	
	@Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
	User existsByUserEmail(String mobileNumber);
	
	@Query(value = "Select * from user left join address on user.id=address.user_id where address.city = ?1", nativeQuery = true)
	List<User> findByCity(String city);

	@Query(value = "Select * from user where name = ?1", nativeQuery = true)
	List<User> findByName(String name);

	@Query(value = "Select * from user left join address on user.id=address.user_id where address.pincode = ?1", nativeQuery = true)
	List<User> findByPincode(String pincode);

	@Query(value = "Select * from user where username = ?1 LIMIT 1", nativeQuery = true)
	User findByUsername(String username);
}
