package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.responsehandling.CustomHttpResponse;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.validation.ValidationException;
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
        try {
            subjectService.createSubject(subject);
        } catch (ValidationException ve) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CustomHttpResponse(400, "Bad Request",
                            "It's mandatory to enter a name for the subject")).build());
        }
        return Response.ok().entity(new CustomHttpResponse(200, "OK",
                "Subject created")).build();
    }

    @Path("addstudent")
    @GET
    public Response addStudentToSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("studentid") Long studentId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Student foundStudent = subjectService.findStudentById(studentId);

        // Check if subject exists
        if (foundSubject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No subject with id: " + subjectId + " found.")).build());
        }
        // Check if student exists
        if (foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No student with id: " + studentId + " found.")).build());
        }

        // Check if student is already assigned to subject
        for (Student student : foundSubject.getStudents()) {
            if (student.getId() == studentId) {
                throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
                        .entity(new CustomHttpResponse(409, "Conflict",
                                "Student with id: " + studentId + " is already assigned to that subject.")).build());
            }
        }
        subjectService.addStudentToSubject(subjectId, studentId);
        return Response.ok().entity(new CustomHttpResponse(200, "OK",
                "Student added to subject")).build();
    }

    @Path("removestudent")
    @DELETE
    public Response removeStudentFromSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("studentid") Long studentId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Student foundStudent = subjectService.findStudentById(studentId);

        // Check if subject exists
        if (foundSubject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No subject with id: " + subjectId + " found.")).build());
        }
        // Check if student exists
        if (foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No student with id: " + studentId + " found.")).build());
        }
        // Check if student is already assigned to subject
        for (Student student : foundSubject.getStudents()) {
            if (student.getId() == studentId) {
                subjectService.removeStudentFromSubject(subjectId, studentId);
                return Response.ok().entity(new CustomHttpResponse(200, "OK",
                        "Student removed from subject.")).build();
            }
        }
        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                .entity(new CustomHttpResponse(404, "Not Found",
                        "Student with id: " + studentId + " is not assigned to that subject.")).build());
    }

    @Path("removeteacher")
    @DELETE
    public Response removeTeacherFromSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("teacherid") Long teacherId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Teacher foundTeacher = subjectService.findTeacherById(teacherId);

        // Check if subject exists
        if (foundSubject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No subject with id: " + subjectId + " found.")).build());
        }
        // Check if teacher exists
        if (foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No teacher with id: " + teacherId + " found.")).build());
        }
        // Check if student is already assigned to subject

        if (foundSubject.getTeacher() != null) {
            if (foundSubject.getTeacher().getId() == teacherId) {
                subjectService.removeTeacherFromSubject(subjectId, teacherId);
                return Response.ok().entity(new CustomHttpResponse(200, "OK",
                        "Teacher removed from subject.")).build();
            }
        }

        throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                .entity(new CustomHttpResponse(404, "Not Found",
                        "Teacher with id: " + teacherId + " is not assigned to that subject.")).build());
    }

    @Path("addteacher")
    @GET
    public Response addTeacherToSubject(@QueryParam("subjectid") Long subjectId, @QueryParam("teacherid") Long teacherId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        Teacher foundTeacher = subjectService.findTeacherById(teacherId);
        // Check if subject exists
        if (foundSubject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No subject with id: " + subjectId + " found.")).build());
        }
        // Check if student exists
        if (foundTeacher == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No teacher with id: " + teacherId + " found.")).build());
        }

        subjectService.addTeacherToSubject(subjectId, teacherId);
        return Response.ok().entity(new CustomHttpResponse(200, "OK",
                "Teacher added to subject.")).build();
    }

    @Path("search")
    @GET
    public Response getAllStudentsAndTeacherFromSubject(@QueryParam("subjectid") Long subjectId) {
        Subject foundSubject = subjectService.findSubjectById(subjectId);
        if (foundSubject == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No subject with id: " + subjectId + " found.")).build());
        }

        return Response.ok(foundSubject).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long subjectId) {
        if (subjectService.findSubjectById(subjectId) == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No subject with id: " + subjectId + " found.")).build());
        }

        subjectService.deleteSubject(subjectId);
        return Response.ok(new CustomHttpResponse(200, "OK",
                "Subject with id: " + subjectId + " deleted.")).build();
    }
}
