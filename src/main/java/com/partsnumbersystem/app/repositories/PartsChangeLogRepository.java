package com.partsnumbersystem.app.repositories;

import com.partsnumbersystem.app.entities.PartsChangeLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Zeeshan on 22/08/2017.
 */
@Repository
public interface PartsChangeLogRepository extends CrudRepository<PartsChangeLog, Integer> {}
