package com.madhu.resources;

import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.madhu.core.Person;
import com.madhu.jdbi.PersonDAO;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * @author Madhukar Reddy
 *
 */
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api("/person")
public class PersonResource {
    
    private static Logger LOGGER = LoggerFactory.getLogger("PersonResource");

    private final PersonDAO personDAO;
    public PersonResource(PersonDAO personDAO, DBI dbi) {
        this.personDAO = dbi != null ? dbi.onDemand(PersonDAO.class) : null;
    }

    @GET
    @Path("/List")
    @ApiOperation("Person List")
    @ApiResponses({ @ApiResponse(code = 200, message = "Persons successfully retrieved.") })
    public List<Person> getAll() {
        LOGGER.info("Getting the list of Persons");
        return personDAO.getAll();
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Person Get")
    @ApiResponses({ @ApiResponse(code = 200, message = "Person successfully retrieved.") })
    public Person get(@PathParam("id") Integer id) {
        LOGGER.debug("Getting person with id {} ",id);
        return personDAO.findById(id);
    }

    @POST
    @ApiOperation("Person Create")
    @ApiResponses({ @ApiResponse(code = 201, message = "Person successfully created.") })
    public Response add(@Valid @ApiParam(required = true, value = "Person to create") Person person) {
        int newId = personDAO.insert(person);
        LOGGER.debug("No of persons inserted {} ",newId);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @PUT
    @Path("/{id}")
    @ApiOperation("Person Update")
    @ApiResponses({ @ApiResponse(code = 200, message = "Person successfully Updated.") })
    public Person update(@PathParam("id") int id,
            @Valid @ApiParam(required = true, value = "Person to update") Person person) {
        LOGGER.debug("updating person with id {} ",id);
        Person person1 = new Person();
        person1.setId(id);
        person1.setName(person.getName());
        personDAO.update(person1);
        return person1;
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Person Delete")
    @ApiResponses({ @ApiResponse(code = 200, message = "Person successfully deleted.") })
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") int id) {
        LOGGER.debug("deleting person with id {} ",id);
        personDAO.deleteById(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

}
