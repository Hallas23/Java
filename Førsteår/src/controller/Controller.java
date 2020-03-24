package controller;

import java.util.Set;

import modelstudent.Student;
import modelstudent.Team;
import storage.Storage;

public class Controller {
    private Storage storage;
    private static Controller controller;

    private Controller() {
        storage = new Storage();
    }

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
    public static Controller getTestController() {
        return new Controller();
    }
    
    public Team createTeam(String name, String room) {
        Team t = new Team(name, room);
        storage.addTeam(t);
        return t;
    }

    public Student createStudent(String name, int age) {
        Student s = new Student(name, age);
        storage.addStudent(s);
        return s;
    }

    public void addStudentToTeam(Student s, Team t) {
        t.addStudent(s);
    }

    public void updateTeam(Team t, String name, String room) {
        t.setName(name);
        t.setRoom(room);
    }

    public Set<Team> getAllTeams() {
        return storage.getAllTeams();
    }

    public Set<Student> getAllStudents() {
        return storage.getAllStudents();
    }

}
