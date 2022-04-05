package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    @Path("{id}")
    @PATCH
    public Response updateTeacher(@PathParam("id") Long teacherId, @QueryParam("newname") String newName){
        Teacher foundTeacher = teacherService.findTeacherFromId(teacherId);
        foundTeacher.setName(newName);
        teacherService.updateTeacher(foundTeacher);

        // TODO: Add error handling and change return to JSON response instead of PLAIN_TEXT


        return Response.ok().entity("Teacher updated").type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
