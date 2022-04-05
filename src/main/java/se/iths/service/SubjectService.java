package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

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

    private Teacher findTeacherById(Long teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    public void addStudentToSubject(Long subjectId, Long studentId) {
        Subject foundSubject = findSubjectById(subjectId);
        Student foundStudent = findStudentById(studentId);

        foundSubject.addStudent(foundStudent);
        entityManager.persist(foundSubject);
    }

    public void addTeacherToSubject(Long subjectId, Long teacherId) {
        Subject foundSubject = findSubjectById(subjectId);
        Teacher foundTeacher = findTeacherById(teacherId);

        foundSubject.setTeacher(foundTeacher);
        entityManager.persist(foundSubject);
    }

    public void deleteSubject(Long subjectId) {
        entityManager.remove(entityManager.find(Subject.class, subjectId));
    }
}
