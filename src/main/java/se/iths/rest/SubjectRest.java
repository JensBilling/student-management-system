package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
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
    public Response createSubject(Subject subject) {

        // TODO: Add error handling and change return to JSON response instead of PLAIN_TEXT

        subjectService.createSubject(subject);

        return Response.ok().entity("Subject created").type(MediaType.TEXT_PLAIN_TYPE).build();
    }

    @Path("addstudent")
    @GET
    public Response addStudentToSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("studentid") Long studentId) {
        subjectService.addStudentToSubject(subjectId, studentId);

        // TODO: Add error handling and change return to JSON response instead of PLAIN_TEXT

        return Response.ok().entity("Student added to subject").type(MediaType.TEXT_PLAIN_TYPE).build();
    }

    @Path("removestudent")
    @DELETE
    public Response removeStudentFromSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("studentid") Long studentId) {
        subjectService.removeStudentFromSubject(subjectId, studentId);

        // TODO: Add error handling and change return to JSON response instead of PLAIN_TEXT

        return Response.ok().entity("Student removed from subject").type(MediaType.TEXT_PLAIN_TYPE).build();

    }

    @Path("addteacher")
    @GET
    public Response addTeacherToSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("teacherid") Long teacherId) {
        subjectService.addTeacherToSubject(subjectId, teacherId);

        // TODO: Add error handling and change return to JSON response instead of PLAIN_TEXT

        return Response.ok().entity("Teacher added to subject").type(MediaType.TEXT_PLAIN_TYPE).build();

    }

    @Path("search")
    @GET
    public Response getAllStudentsAndTeacherFromSubject(@QueryParam("subjectid") Long subjectId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);

        // TODO: Add error handling and change return to JSON response instead of PLAIN_TEXT

        return Response.ok(foundSubject).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long subjectId){
        subjectService.deleteSubject(subjectId);

        // TODO: Add error handling and change return to JSON response instead of PLAIN_TEXT

        return Response.ok("Subject with id: " + subjectId + " deleted.").build();
    }
}
