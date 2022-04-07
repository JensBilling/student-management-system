package se.iths.rest;

import se.iths.entity.Student;
import se.iths.responsehandling.CustomHttpResponse;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {

        if (studentService.isEmailAddressAlreadyUsed(student.getEmail())) {
            throw new WebApplicationException(Response.status(Response.Status.CONFLICT)
                    .entity(new CustomHttpResponse(409, "Conflict",
                            "The email-address is already in use")).build());
        }

        try {
            studentService.createStudent(student);
        } catch (ValidationException ve) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new CustomHttpResponse(400, "Bad Request",
                           "It's mandatory to enter your first name, last name and email-address")).build());
        }

        return Response.ok().entity(new CustomHttpResponse(200, "OK",
                "Student created")).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        if (studentService.findAllStudents().size() < 1){
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No students found")).build());
        }
        return Response.ok(studentService.findAllStudents()).build();
    }

    @Path("search")
    @GET
    public Response getStudentsFromLastName(@QueryParam("lastname") String lastName) {
        List<Student> foundStudents = studentService.findStudentsFromLastName(lastName);
          if (foundStudents.size() == 0) {

              throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                      .entity(new CustomHttpResponse(404, "Not Found",
                              "No students found with last name: " + lastName + ".")).build());
        }
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateStudent(@PathParam("id") Long studentId, Student student) {
        //Make sure a student with that ID already exists
        Student foundStudent = studentService.findStudentById(studentId);

        if (foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No student found with id: " + studentId + ".")).build());
        }

        //Only apply changes that are not null
        if (student.getFirstName() != null) {
            foundStudent.setFirstName(student.getFirstName());
        }
        if (student.getLastName() != null) {
            foundStudent.setLastName(student.getLastName());
        }
        if (student.getEmail() != null) {
            foundStudent.setEmail(student.getEmail());
        }
        if (student.getPhoneNumber() != null) {
            foundStudent.setPhoneNumber(student.getPhoneNumber());
        }

        // Update database with new values
        studentService.updateStudent(foundStudent);

        //Return updated student in response
        return Response.ok(foundStudent).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long studentId) {
        Student foundStudent = studentService.findStudentById(studentId);
        if (foundStudent == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new CustomHttpResponse(404, "Not Found",
                            "No student found with id: " + studentId + ".")).build());
        }
        studentService.deleteStudent(studentId);
        return Response.ok().entity(new CustomHttpResponse(200, "OK",
                        "Student with id: " + studentId + " deleted from database.")).build();
    }
}
