# Simple Java EE REST student management system.


## Create new student
**POST request** localhost:8080/student-management-system/api/v1/students

With JSON request body:
``` Json
{
	"firstName": "John",
	"lastName": "Doe",
	"email": "john.doe@email.com",
	"phoneNumber": "0123456789"
}
```

firstName, lastName and email are mandatory, phoneNumber is optional.

## Get a list of all the students
**GET request** localhost:8080/student-management-system/api/v1/students

## Get a list of all students with a specific last name
**GET request** localhost:8080/student-management-system/api/v1/students/search?lastname=*{lastname}* 

Replace *{lastname}* in the query parameter with an string search query.

## Update student 
**PATCH request** localhost:8080/student-management-system/api/v1/students/*{studentId}*" 

Replace *{studentId}* in the Path with the id of the student you want to modify. 

Include updated information in the request message body:

``` Json
{
		"email": "new@email.com",
		"phoneNumber": "0701234567"
}
```

## Delete student
**DELETE request** localhost:8080/student-management-system/api/v1/students/*{studentId}* 

Replace *{studentId}* in the Path with the id of the student you want to delete.

## Create Subject
**Post request** localhost:8080/student-management-system/api/v1/subjects

With JSON request body:
``` Json
{
	"subject": "Svenska"
}
```

## Delete subject
**DELETE request** localhost:8080/student-management-system/api/v1/subjects/*{subjectId}*

Replace *{subjectId}* in the Path with the id of the subject you want to delete.

## Create teacher
**POST request** localhost:8080/student-management-system/api/v1/teachers

With JSON request body:
``` Json
{
	"name": "Lasse"
}
```

## Update teacher
**PATCH request** localhost:8080/student-management-system/api/v1/teachers/*{teacherId}*?newname=*{newName}*

Replace *{teacherID}* in the Path with the id of the teacher you want to update.
Replace *{newName}* in the query parameter with the updated name for the teacher.

## Delete teacher
**DELETE request** localhost:8080/student-management-system/api/v1/teachers/*{teacherId}*

Replace *{teacherID}* in the Path with the id of the teacher you want to delete.

## Add a student to a subject
**GET request** localhost:8080/student-management-system/api/v1/subjects/addstudent?subjectid=*{subjectId}*&studentid=*{studentId}*

Replace *{subjectId}* in the query parameter with the id of the subject you want to add the student to.
Replace *{studentId}* in the query parameter with the id of the student you want to add.

## Remove a student from a subject
**DELETE request** localhost:8080/student-management-system/api/v1/subjects/removestudent?subjectid=*{subjectId}*&studentid=*{studentId}*
Replace *{subjectId}* in the query parameter with the id of the subject you want to remove a student from.
Replace *{studentId}* in the query parameter with the id of the student you want to remove.

## Add a teacher to a subject
**GET request** localhost:8080/student-management-system/api/v1/subjects/addteacher?subjectid=*{subjectId}*&teacherid=*{teacherId}*

Replace *{subjectId}* in the query parameter with the id of the subject you want to add the teacher to.
Replace *{teacherId}* in the query parameter with the id of the teacher you want to add.

**Note that adding a teacher to a subject that already has a teacher, will replace the existing teacher with the new one**

## Remove a teacher from a subject
**GET request** localhost:8080/student-management-system/api/v1/subjects/removeteacher?subjectid=*{subjectId}*&teacherid=*{teacherId}*

Replace *{subjectId}* in the query parameter with the id of the subject you want to remove the teacher from.
Replace *{teacherId}* in the query parameter with the id of the teacher you want to remove.

## Get all available information about a specific subject
**GET request** localhost:8080/student-management-system/api/v1/subjects/search?subjectid=*{subjectId}*

Replace *{subjectId}* in the query parameter with the id of the subject you want to retrieve.
