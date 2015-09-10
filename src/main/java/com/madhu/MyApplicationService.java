package com.madhu;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.skife.jdbi.v2.DBI;
import com.madhu.command.RunMigrationsCommand;
import com.madhu.health.DaatBaseHealthCheck;
import com.madhu.jdbi.PersonDAO;
import com.madhu.resources.HelloWorldResource;
import com.madhu.resources.PersonResource;

/**
 * @author Madhukar Reddy
 *
 */
public class MyApplicationService extends Application<MyApplicationConfiguration> {

    public static void main(String[] args) throws Exception {
        new MyApplicationService().run(args);
    }

    @Override
    public void initialize(Bootstrap<MyApplicationConfiguration> bootstrap) {
        //DB migrate Coomand
        bootstrap.addCommand(new RunMigrationsCommand());
        //Swagger configuration goes here
        bootstrap.addBundle(new SwaggerBundle<MyApplicationConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(MyApplicationConfiguration configuration) {
                configuration.getSwagger().setResourcePackage(PersonResource.class.getPackage().getName());
                return configuration.getSwagger();
            }
        });
    }

    @Override
    public void run(MyApplicationConfiguration configuration, Environment environment) throws Exception {

        //JDBI Configuration goes here
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "h2");

        final PersonDAO personDAO = jdbi.onDemand(PersonDAO.class);
        personDAO.createPersonTable();
        final PersonResource personResource = new PersonResource(personDAO);

        //Registering Resources
        environment.jersey().register(new HelloWorldResource(configuration.getTemplate()));
        environment.jersey().register(personResource);

        //Health check-up goes here
        environment.healthChecks().register("database", new DaatBaseHealthCheck(configuration));
    }

}
