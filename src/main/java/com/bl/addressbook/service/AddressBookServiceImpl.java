package com.bl.addressbook.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bl.addressbook.dto.UserDTO;
import com.bl.addressbook.model.Address;
import com.bl.addressbook.model.User;
import com.bl.addressbook.repository.AddressBookRepo;
import com.bl.addressbook.util.TokenUtil;
import com.bl.addressbook.exception.UserNotFoundException;
import com.bl.addressbook.exception.LoginException;
import com.bl.addressbook.exception.UserAlreadyExistsException;

@Service
public class AddressBookServiceImpl implements IAddressBookService {

	@Autowired
	private AddressBookRepo addressBookRepo;

	@Autowired
	TokenUtil tokenUtil;

	@Override
	public List<User> getUsers(String isLogin) throws LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			return addressBookRepo.findAll();
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public User getUser(String isLogin, String token) throws UserNotFoundException, LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			Long id = tokenUtil.decodeToken(token);
			if (addressBookRepo.existsById(id)) {
				return addressBookRepo.findById(id).get();
			} else {
				throw new UserNotFoundException("User Not Found");
			}
		} else {
			throw new LoginException("Access Denied");
		}

	}

	@Override
	public User addUser(String isLogin, UserDTO userDTO) throws LoginException {
//		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			if (addressBookRepo.existsByUsermobileNumber(userDTO.getMobileNumber()) == null) {
				User user = new User(userDTO);
				Address address = user.getAddress();

				address.setUser(user);

				user.setAddress(address);
				return addressBookRepo.save(user);
			} else {
				throw new UserAlreadyExistsException();
			}
//		} else {
//			throw new LoginException("Access Denied");
//		}

	}

	@Override
	public User editUser(String isLogin, String token, UserDTO userDTO) throws UserNotFoundException, LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			Long id = tokenUtil.decodeToken(token);
			if (addressBookRepo.findById(id).isPresent()) {
				User getUser = addressBookRepo.findById(id).get();
				getUser.setName(userDTO.getName());
				getUser.setMobileNumber(userDTO.getMobileNumber());
				getUser.setUsername(userDTO.getUsername());
				getUser.setPassword(userDTO.getPassword());
				getUser.setUpdated_at(LocalDateTime.now());

				Address address = getUser.getAddress();
				address.setArea(userDTO.getAddress().getArea());
				address.setCity(userDTO.getAddress().getCity());
				address.setState(userDTO.getAddress().getState());
				address.setCountry(userDTO.getAddress().getCountry());
				address.setPincode(userDTO.getAddress().getPincode());
				address.setUpdated_at(LocalDateTime.now());
				address.setUser(getUser);

				getUser.setAddress(address);

				return addressBookRepo.save(getUser);
			} else {
				throw new UserNotFoundException("User Not Found");
			}
		} else {
			throw new LoginException("Access Denied");
		}

	}

	@Override
	public void deleteUser(String isLogin, String token) throws UserNotFoundException, LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			Long id = tokenUtil.decodeToken(token);
			if (addressBookRepo.findById(id).isPresent()) {
				User user = addressBookRepo.findById(id).get();
				addressBookRepo.delete(user);
			} else {
				throw new UserNotFoundException("User Not Found");
			}
		} else {
			throw new LoginException("Access Denied");
		}

	}

	@Override
	public void deleteAllUser(String isLogin) throws LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			addressBookRepo.deleteAll();
		} else {
			throw new LoginException("Access Denied");
		}

	}

	@Override
	public List<User> findUserByCity(String isLogin, String city) throws LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			return addressBookRepo.findByCity(city);
		} else {
			throw new LoginException("Access Denied");
		}

	}

	@Override
	public List<User> findUserByName(String isLogin, String name) throws LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			return addressBookRepo.findByName(name);
		} else {
			throw new LoginException("Access Denied");
		}

	}

	@Override
	public List<User> findUserByPincode(String isLogin, String pincode) throws LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			return addressBookRepo.findByPincode(pincode);
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public String checkUser(String username, String password) throws UserNotFoundException, LoginException {
		User user = addressBookRepo.findByUsername(username);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return tokenUtil.createToken(user.getId());
			} else {
				throw new LoginException("Wrong Password!");
			}
		} else {
			throw new UserNotFoundException("User Not Found");
		}
	}

	@Override
	public List<User> sortAscUser(String isLogin, String key) throws LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			return addressBookRepo.findAll(Sort.by(Sort.Direction.ASC, key));			
		} else {
			throw new LoginException("Access Denied");
		}
	}

	@Override
	public List<User> sortDescUser(String isLogin, String key) throws LoginException {
		if (addressBookRepo.findById(tokenUtil.decodeToken(isLogin)).isPresent()) {
			return addressBookRepo.findAll(Sort.by(Sort.Direction.DESC, key));
		} else {
			throw new LoginException("Access Denied");
		}
	}
}
