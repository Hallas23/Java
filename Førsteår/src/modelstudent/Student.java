package modelstudent;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse der beskriver en studerende
 *
 * @author mad
 *
 */
public class Student {

    private String name;
    private int age;
    private List<Integer> grades; // indeholder den studerendes karakterer

    private static final int MAX_NUMBER_OF_GRADES = 10;// det hoejeste antal
    // karakterer en
    // studerende kan have

    /*
     * Constructor, naar den studerende oprettes, skal den have et navn. Ved
     * oprettelse er den studerende ikke aktiv
     */
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        grades = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void addGrade(int grade) {
        if (grades.size() < MAX_NUMBER_OF_GRADES) {
            grades.add(grade);
        }
        else {
            throw new TooManyGradesException("For mange karakterer givet");
        }
    }

    public double averageGrade() {
        int sum = 0;
        double average = 0.0;
        for (int i = 0; i < grades.size(); i++) {
            sum = sum + grades.get(i);
        }
        average = (double) sum / grades.size();
        return average;
    }

}
