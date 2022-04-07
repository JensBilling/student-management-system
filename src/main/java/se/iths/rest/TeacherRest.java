package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.responsehandling.CustomHttpResponse;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.validation.ValidationException;
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
    public Response createTeacher(Teacher teacher) {

        try {
            teacherService.createTeacher(teacher);
        } catch (ValidationException ve) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CustomHttpResponse(400, "Bad Request",
                            "It's mandatory to enter a name for the teacher")).build());
        }
        return Response.ok().entity(new CustomHttpResponse(200, "OK",
                "Teacher created")).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateTeacher(@PathParam("id") Long teacherId, @QueryParam("newname") String newName) {
        //Make sure a teacher with that ID already exists
        Teacher foundTeacher = teacherService.findTeacherFromId(teacherId);

        if (foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No teacher found with id: " + teacherId + ".")).build());
        }

        try {
            foundTeacher.setName(newName);
        } catch (ValidationException ve) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CustomHttpResponse(400, "Bad Request",
                            "It's mandatory to enter a name for the teacher")).build());
        }
        teacherService.updateTeacher(foundTeacher);

        return Response.ok().entity(new CustomHttpResponse(200, "OK",
                "Teacher updated")).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long teacherId) {

        Teacher foundTeacher = teacherService.findTeacherFromId(teacherId);
        if (foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No teacher found with id: " + teacherId + ".")).build());
        }

        // Check if teacher is assigned to any subjects

        teacherService.deleteTeacher(teacherId);
        return Response.ok().entity(new CustomHttpResponse(200, "OK",
                "Teacher with id: " + teacherId + " deleted from database.")).build();
    }
}
