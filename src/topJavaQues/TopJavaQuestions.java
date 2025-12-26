package topJavaQues;

import java.util.ArrayList;
import java.util.List;

public class TopJavaQuestions {

    // Immutability class final to prevent inheritance and prevent method override
    // private and final variable
    public final static class Student {
        private final String name;
        private final List<String> add;

        public Student(String name, List<String> add) {
            this.name = name;
            // copy store bcz add is pointer to memory hence can be modified
            this.add = new ArrayList<>(add);
        }

        public String getName() {
            return name;
        }

        // return only clone to prevent .add like student.getAdd().add(anyValue)
        public List<String> getAdd() {
            return new ArrayList<>(add);
        }

        @Override
        public String toString() {
            return "name: " + name + ", address:" + add;
        }
    }

    public static void main(String[] args) {
        List<String> add = new ArrayList<>();
        add.add("HOME");
        Student st = new Student("vijay", add);
        add.add("ra");
        add.add("sdfsfg");
        st.getAdd().add("OFFC");
        System.out.println(st);
    }

}
