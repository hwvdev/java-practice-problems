import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Groupby {

    public static class Student {
        int id;
        String name;
        String institute;

        public  Student(int id, String name, String institute) {
            this.id = id;
            this.name = name;
            this.institute = institute;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInstitute() {
            return institute;
        }

        public void setInstitute(String institute) {
            this.institute = institute;
        }

        @Override
        public String toString() {
            return "Student [id=" + id + ", name=" + name + ", institute=" + institute;
        }
    }

    public static void main(String[] args) {

        List<Student> students = new ArrayList<Student>();
        students.add(new Student(12, "vijay", "IIT"));
        students.add(new Student(113, "aman", "LODU"));
        students.add(new Student(67, "abc", "IIT"));
        students.add(new Student(32, "ajay", "RAND"));
        students.add(new Student(34, "jay", "IIT"));
        students.add(new Student(334, "jay", "RAND"));
    }
}
