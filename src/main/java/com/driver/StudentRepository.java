package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    Map<String,Student> studentDb = new HashMap<>();
    Map<String,Teacher> teacherDb = new HashMap<>();
    Map<String, List<String>> studentTeacherDb = new HashMap<>();

    public void addStudent(Student student){
        studentDb.put(student.getName(),student);
    }

    public void addStudent(Teacher teacher){
        teacherDb.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher){

        if(!studentTeacherDb.containsKey(teacher)){
            studentTeacherDb.put(teacher,new ArrayList<>());
        }
        studentTeacherDb.get(teacher).add(student);
    }

    public Student getStudentByName(String name){
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name){
        return teacherDb.get(name);
    }

    public List<String> getAllStudents(){
        List<String> studentNames = new ArrayList<>();
        for(String name: studentDb.keySet()){
            studentNames.add(name);
        }

        return studentNames;
    }

    public List<String> getStudentsByTeacherName(String name) {
        return studentTeacherDb.get(name);
    }

    public void deleteTeacherByName(String teacher) {

        for(String student: studentTeacherDb.get(teacher))
            studentDb.remove(student);

        studentTeacherDb.remove(teacher);
        teacherDb.remove(teacher);
    }

    public void deleteAllTeachers() {

        for(String teacher: teacherDb.keySet()){
            deleteTeacherByName(teacher);
        }
    }
}
