public class ShallowCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Original object
        Employee emp = new Employee("John", 1001);
        Department dept = new Department("IT", emp);
        
        // Shallow clone
        Department shallowDept = dept.clone();
        
        // Test
        System.out.println("Original: " + dept);  // John
        System.out.println("Clone: " + shallowDept);  // John

        // Modify through clone
        shallowDept = shallowDept.withName("yess");
        shallowDept= shallowDept.withManager("Mujhe nhi pta");
        System.out.println("\nAfter modifying clone's manager:");
        System.out.println("Original: " + dept);  // Mike! Changed!
        System.out.println("Clone: " + shallowDept);  // Mike
        
        // Primitive/String changes don't affect original
//        shallowDept.name = "HR";
        System.out.println("\nAfter modifying clone's name:");
        System.out.println("Original dept: " + dept);  // IT (unchanged)
        System.out.println("Clone dept: " + shallowDept);  // HR
    }

    final static class Department implements Cloneable {
        final String name;
        final Employee manager;
        public Department(String name, Employee emp) {
            this.manager = new Employee(emp.name, emp.id);
            this.name = name;
        }
        public Department clone() throws CloneNotSupportedException {
            return new Department(this.name, this.manager);
        }
        public Department withName(String name) {
            return new Department(name, this.manager);
        }
        public Department withManager(String empName) {
            Employee newEmp = new Employee(empName, this.manager.id);
            return new Department(this.name, newEmp);
        }

        @Override
        public String toString() {
            return "Department{" +
                    "name='" + name + '\'' +
                    ", manager=" + manager +
                    '}';
        }
    }
    final static class Employee {
        final String name;
        final int id;

        public Employee(String name, int id) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}