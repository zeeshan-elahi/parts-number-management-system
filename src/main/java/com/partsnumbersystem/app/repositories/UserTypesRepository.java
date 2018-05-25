package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.UserTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zeeshan on 04/09/2017.
 */
@Repository
public interface UserTypesRepository extends CrudRepository<UserTypes, Integer> {
}
