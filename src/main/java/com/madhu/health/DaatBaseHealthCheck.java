package com.madhu.health;

import org.h2.engine.Database;
import org.skife.jdbi.v2.DBI;
import com.codahale.metrics.health.HealthCheck;

/**
 * @author Madhukar Reddy
 *
 */
public class DaatBaseHealthCheck extends HealthCheck{

    private final DBI database;
    
    public DaatBaseHealthCheck(DBI database) {
        this.database = database;
    }
    
    @Override
    protected Result check() throws Exception {
        if (database.open().isInTransaction()) {
            return Result.healthy();
        }
        return Result.unhealthy("Can't reach database");
    }

}
