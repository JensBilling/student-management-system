package se.iths.service;

import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }


    public Teacher findTeacherFromId(Long teacherId) {
        return entityManager.find(Teacher.class, teacherId);
    }

    public void updateTeacher(Teacher foundTeacher) {
        entityManager.merge(foundTeacher);
    }
}
