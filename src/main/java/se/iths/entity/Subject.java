package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    private String subject;

    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    private Teacher teacher;

    public Subject() {
    }

    public Subject(String subject, List<Student> students, Teacher teacher) {
        this.subject = subject;
        this.students = students;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> studentsInSubject) {
        this.students = studentsInSubject;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void removeStudent(Long studentId) {
        int indexOfStudent = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId){
                indexOfStudent = i;
            }
        }
        if (indexOfStudent >= 0){
            students.remove(indexOfStudent);
        }
    }
}
