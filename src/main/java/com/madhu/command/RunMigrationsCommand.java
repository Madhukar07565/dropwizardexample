package com.madhu.command;

import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import com.codahale.metrics.MetricRegistry;
import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.MigrationVersion;
import com.googlecode.flyway.core.migration.SchemaVersion;
import com.madhu.MyApplicationConfiguration;

/**
 * @author Madhukar Reddy
 *
 */
public class RunMigrationsCommand extends ConfiguredCommand<MyApplicationConfiguration> {
    

    public RunMigrationsCommand() {
        super("migrate", "Run DB Migrations");
    }

    @Override
    protected void run(Bootstrap<MyApplicationConfiguration> bootstrap, Namespace namespace,
            MyApplicationConfiguration configuration) throws Exception {

        ManagedDataSource ds = configuration.getDatabase().build(new MetricRegistry(), "migrate");
        ds.start();
        try {
            Flyway flyway = new Flyway();
            flyway.setDataSource(ds);
            flyway.setLocations("classpath:db/migration");
            flyway.setInitVersion("0");
            flyway.setInitOnMigrate(true);
            flyway.migrate();
        } finally {
            ds.stop();
        }
    }


}
