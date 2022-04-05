package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public Subject findSubjectById(Long subjectId) {
        return entityManager.find(Subject.class, subjectId);
    }

    public Student findStudentById(Long studentId) {
        return entityManager.find(Student.class, studentId);
    }

    public void addStudentToSubject(Long subjectId, Long studentId) {
        Subject foundSubject = findSubjectById(subjectId);
        Student foundStudent = findStudentById(studentId);

        foundSubject.addStudent(foundStudent);
        entityManager.persist(foundSubject);
    }

}
