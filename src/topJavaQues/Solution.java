package topJavaQues;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int b) {
            this.val= b;
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> subPre = new ArrayList<>();
        preorder(root, pre);
        preorder(subRoot, subPre);
        List<Integer> inord = new ArrayList<>();
        List<Integer> subInord = new ArrayList<>();
        inorder(root, inord);
        inorder(subRoot, subInord);

        return subarrayMatching(pre, subPre) && subarrayMatching(inord, subInord);

    }

    public void inorder(TreeNode root, List<Integer> arr) {
        if(root==null) return;

        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
    }


    public void preorder(TreeNode root, List<Integer> pre) {
        if(root==null) return;
        pre.add(root.val);
        preorder(root.left, pre);
        preorder(root.right, pre);
    }

    public boolean subarrayMatching(List<Integer> a, List<Integer> b) {
        double hashA = 0;
        double hashB = 0;
        int PRIME = 11;

        for (int i = 0; i < b.size(); i++) {
            hashA+=Math.pow(PRIME, i)*a.get(i);
            hashB+=Math.pow(PRIME, i)*b.get(i);
        }

        for (int i = b.size(); i < a.size(); i++) {
            if (hashA==hashB) {
                if (match(a, i-b.size(), i-1 , b))
                    return true;
            }
            hashA = (hashA - a.get(i-b.size()))/PRIME + Math.pow(PRIME, b.size()-1)*a.get(i);
        }
        return false;
    }

    public boolean match(List<Integer> a, int i, int j, List<Integer> b) {
        int x=0;
        for (int k = i; k <=j; k++) {
            if (a.get(k)!=b.get(x)) return false;
            x++;
        }
        return true;
    }

    public static class Person {
        String name;
        int age;
        GENDER gender;

        public enum GENDER {
            MALE, FEFAME, BI, UNKNOWN
        }
        public Person(int age, String name, GENDER gender) {
            this.age = age;
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name=" + name +
                    ", age=" + age +
                    ", gender=" + gender +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person(12, "vijay", Person.GENDER.MALE);
        Person p2 = new Person(124, "asd", Person.GENDER.FEFAME);
        Person p3 = new Person(121, "fds", Person.GENDER.MALE);
        Person p4 = new Person(132, "ops", Person.GENDER.BI);
        Person p5 = new Person(212, "fdr", Person.GENDER.UNKNOWN);
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);
        printPersonWithFilter(personList, p -> p.gender.equals(Person.GENDER.UNKNOWN), p-> System.out.println(p));
    }

    public static void printPersonWithFilter(List<Person> personList, Predicate<Person> filter, Consumer<Person> consumer) {
        for (Person p : personList) {
            if (filter.test(p)) {
                consumer.accept(p);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.indexOf("");
    }
}