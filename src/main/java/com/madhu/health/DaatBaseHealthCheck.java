package com.madhu.health;

import com.codahale.metrics.health.HealthCheck;
import com.madhu.MyApplicationConfiguration;

/**
 * @author Madhukar Reddy
 *
 */
public class DaatBaseHealthCheck extends HealthCheck {

    private MyApplicationConfiguration configuration;

    public DaatBaseHealthCheck(MyApplicationConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected Result check() throws Exception {
        if (configuration.getDatabase().getCheckConnectionOnConnect()) {
            return Result.healthy();
        }
        return Result.unhealthy("Server is not Up");
    }

}
