package com.madhu;

import static org.junit.Assert.assertEquals;
import java.util.Random;
import io.dropwizard.testing.junit.DropwizardAppRule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;
import com.madhu.core.Person;

/**
 * @author Madhukar Reddy
 *
 */
public class MyApplicationServiceTest {

    @ClassRule
    public static final DropwizardAppRule<MyApplicationConfiguration> RULE = new DropwizardAppRule<MyApplicationConfiguration>(
            MyApplicationService.class, "config.yaml");

    Client client = new JerseyClientBuilder().build();
    Response response;

    @Test
    public void testHelloWorldResource() {

        response = client.target(String.format("http://localhost:%d/hello?name=madhu", RULE.getLocalPort())).request()
                .get();

        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

    }

    @Test
    public void testPersonResource() {
        int id = new Random().nextInt();
        String name = "name" + id;
        response = client.target(String.format("http://localhost:%d/person", RULE.getLocalPort())).request()
                .post(Entity.entity(new Person(id, name), MediaType.APPLICATION_JSON));

        assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
    }

}
