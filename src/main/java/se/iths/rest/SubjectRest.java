package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubject(Subject subject){

        // TODO: Add error handling and change return to JSON response instead of PLAIN_TEXT

        subjectService.createSubject(subject);

        return Response.ok().entity("Subject created").type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
