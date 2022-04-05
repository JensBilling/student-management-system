package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher){

        // TODO: Add error handling and change return to JSON response instead of PLAIN_TEXT

        teacherService.createTeacher(teacher);

        return Response.ok().entity("Teacher created").type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
