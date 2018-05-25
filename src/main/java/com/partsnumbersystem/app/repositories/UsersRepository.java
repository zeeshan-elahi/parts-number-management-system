package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zeeshan on 21/08/2017.
 */
@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    Users getFirstByUserLogin(String userLogin);
}
