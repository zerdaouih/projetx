package org.projetx.repo;

import org.projetx.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUserName(String userName);
	Iterable<User> findAll();
}
