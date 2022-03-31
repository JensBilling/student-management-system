# student-management-system
Simple Java EE REST student management system.

Create and add a new student to the database by sending a POST request to the URL "localhost:8080/student-management-system/api/v1/students". The request message body should include a JSON object of the student formatted in the following way:

{
	"firstName": "John",
	"lastName": "Doe",
	"email": "john.doe@email.com",
	"phoneNumber": "0123456789"
}

firstName, lastName and email are mandatory, phoneNumber is optional.

To get a list of all the students send a GET request to the URL "localhost:8080/student-management-system/api/v1/students"

You can search through all students by last name with a GET request to the URL "localhost:8080/student-management-system/api/v1/students/search?lastname=XXX" but replacing the "XXX" in the query parameter with an actual last name search query.

Update the information of a student with a PATCH request to the URL "localhost:8080/student-management-system/api/v1/students/X" but replace the "X" in the Path with the id of the student you want to modify. Also send the updated information as a JSON object with the request message body, such as below:

{
		"email": "new@email.com",
		"phoneNumber": "0701234567"
}

Delete a student from the database by sending a DELETE request to the URL "localhost:8080/student-management-system/api/v1/students/X" but replace the "X" in the Path with the id of the student you want to delete.
