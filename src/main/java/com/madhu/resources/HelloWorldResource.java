package com.madhu.resources;

import io.dropwizard.jersey.caching.CacheControl;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.annotation.Timed;
import com.madhu.api.Greeting;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @author Madhukar Reddy
 *
 */
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Api("/hello")
public class HelloWorldResource {

    
    private final String template;
    private final AtomicLong counter;

    public HelloWorldResource(String template) {
        this.template = template;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed(name = "get-requests")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    @ApiOperation("Hello World Test")
    public Greeting sayHello(@QueryParam("name") String name) {
        return new Greeting(String.format(template, name), counter.incrementAndGet());
    }
    
}
