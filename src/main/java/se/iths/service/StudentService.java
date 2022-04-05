package se.iths.service;

import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void createStudent(Student student) {
        entityManager.persist(student);
    }

    public List<Student> findAllStudents() {
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    public void updateStudent(Student student) {
        entityManager.merge(student);
    }

    public void deleteStudent(Long studentId) {
        entityManager.remove(entityManager.find(Student.class, studentId));
    }

    public List<Student> findStudentsFromLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = ?1", Student.class);

        return query.setParameter(1, lastName).getResultList();
    }

    public Student findStudentById(Long studentId) {
        return entityManager.find(Student.class, studentId);
    }

    public boolean isEmailAddressAlreadyUsed(String email) {
        List<String> listOfEmails = entityManager.createQuery("SELECT s.email FROM Student s", String.class).getResultList();
        for (String emailInDatabase : listOfEmails) {
            if (emailInDatabase.equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}
