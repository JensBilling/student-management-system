package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
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

        if (studentService.isEmailAddressAlreadyUsed(student.getEmail())){
            return Response.status(Response.Status.CONFLICT)
                    .entity("E-mail address " + student.getEmail() + " is already taken.").type(MediaType.TEXT_PLAIN_TYPE).build();
        }
        // TODO: make sure all fields are required except phone number

        studentService.createItem(student);
        return Response.ok().entity("Student created").type(MediaType.TEXT_PLAIN_TYPE).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        return Response.ok(studentService.findAllStudents()).build();
    }

    @Path("search")
    @GET
    public Response getStudentsFromLastName(@QueryParam("lastname") String lastName) {
        List<Student> foundStudents = studentService.findStudentsFromLastName(lastName);

        if (foundStudents.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No students found with last name: " + lastName + ".").type(MediaType.TEXT_PLAIN_TYPE).build();
        }
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateStudent(@PathParam("id") Long studentId, Student student) {
        //Make sure a student with that ID already exists
        Student foundStudent = studentService.findStudentById(studentId);

        if (foundStudent == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No student found with id: " + studentId + ".").type(MediaType.TEXT_PLAIN_TYPE).build();
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
    public Response deleteStudent(@PathParam("id") Long studentId){
        Student foundStudent = studentService.findStudentById(studentId);
        if (foundStudent == null){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No student found with id: " + studentId + ".").type(MediaType.TEXT_PLAIN_TYPE).build();
        }
        studentService.deleteStudent(studentId);
        return Response.ok().entity("Student with id: " + studentId + " deleted from database.")
                .type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
