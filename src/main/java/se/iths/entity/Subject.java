package se.iths.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String subject;

    @ManyToMany
    private List<Student> studentsInSubject = new ArrayList<>();

    @ManyToOne
    private Teacher teacher;

    public Subject() {
    }

    public Subject(String subject, List<Student> studentsInSubject, Teacher teacher) {
        this.subject = subject;
        this.studentsInSubject = studentsInSubject;
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Student> getStudentsInSubject() {
        return studentsInSubject;
    }

    public void setStudentsInSubject(List<Student> studentsInSubject) {
        this.studentsInSubject = studentsInSubject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
