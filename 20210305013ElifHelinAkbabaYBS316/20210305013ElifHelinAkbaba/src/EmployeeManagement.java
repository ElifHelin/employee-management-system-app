import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

interface Printable {
    void printDetails();
}

class MyGenericClass<T> {
    private T data;

    public MyGenericClass(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

class Manager extends Employee {
    String department;

    Manager(int employeeId, String name, String contact, String gender, String position, double salary, String department) {
        super(employeeId, name, contact, gender, position, salary);
        this.department = department;
    }

    @Override
    public void printDetails() {
        System.out.println("Manager ID: " + this.employeeId + ", Name: " + this.name + ", Position: " + this.position + ", Department: " + this.department);
    }
}

class Employee implements Serializable, Printable {
    int employeeId;
    String name;
    String contact;
    String gender;
    String position;
    double salary;

    Employee() {
        this.name = "";
    }

    Employee(int employeeId, String name, String contact, String gender, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public void printDetails() {
        System.out.println("Employee ID: " + this.employeeId + ", Name: " + this.name + ", Position: " + this.position);
    }
}

public class EmployeeManagement {
    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> stringList = new ArrayList<>();
    static HashMap<Integer, String> hashMap = new HashMap<>();

    static void addEmployee() {
        System.out.print("\nEnter employee ID: ");
        int employeeId = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        System.out.print("Enter employee name: ");
        String name = sc.nextLine();
        System.out.print("Enter contact number: ");
        String contact = sc.nextLine();
        System.out.print("Enter gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter position: ");
        String position = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        Employee emp = new Employee(employeeId, name, contact, gender, position, salary);
        employees.add(emp);
        System.out.println("Employee added successfully.");
    }

    static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }
        System.out.println("\nEmployee List:");
        System.out.println("ID\tName\tContact\t\tGender\tPosition\tSalary");
        employees.forEach(emp -> {
            System.out.println(emp.employeeId + "\t" + emp.name + "\t" + emp.contact + "\t" +
                    emp.gender + "\t" + emp.position + "\t\t" + emp.salary);
        });
    }

    static void deleteEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees to delete.");
            return;
        }
        displayEmployees();
        System.out.print("Enter employee ID to delete: ");
        int idToDelete = sc.nextInt();

        employees.removeIf(emp -> emp.employeeId == idToDelete);

        System.out.println("Employee with ID " + idToDelete + " deleted successfully.");
    }

    static void editEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees to edit.");
            return;
        }
        displayEmployees();
        System.out.print("Enter employee ID to edit: ");
        int idToEdit = sc.nextInt();

        employees.forEach(emp -> {
            if (emp.employeeId == idToEdit) {
                System.out.println("Enter new details for employee ID " + idToEdit + ":");
                sc.nextLine(); // Consume newline left-over
                System.out.print("Enter new employee name: ");
                emp.name = sc.nextLine();
                System.out.print("Enter new contact number: ");
                emp.contact = sc.nextLine();
                System.out.print("Enter new gender: ");
                emp.gender = sc.nextLine();
                System.out.print("Enter new position: ");
                emp.position = sc.nextLine();
                System.out.print("Enter new salary: ");
                emp.salary = sc.nextDouble();
            }
        });

        System.out.println("Employee with ID " + idToEdit + " edited successfully.");
    }
    static void filterEmployeesBySalary(double minSalary) {
        List<Employee> filteredEmployees = employees.stream()
                .filter(employee -> employee.salary >= minSalary)
                .collect(Collectors.toList());

        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees found with salary greater than or equal to " + minSalary);
            return;
        }

        System.out.println("\nEmployees with salary greater than or equal to " + minSalary + ":");
        System.out.println("ID\tName\tContact\t\tGender\tPosition\tSalary");
        filteredEmployees.forEach(employee -> {
            System.out.println(employee.employeeId + "\t" + employee.name + "\t" + employee.contact + "\t" +
                    employee.gender + "\t" + employee.position + "\t\t" + employee.salary);
        });
    }

    public static void main(String[] args) {
        int choice;
        char wish;
        do {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Delete Employee");
            System.out.println("4. Edit Employee");
            System.out.println("5. Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    editEmployee();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

            System.out.println("\nContinue? (y/n)");
            wish = sc.next().charAt(0);
        } while (wish == 'y' || wish == 'Y');
    }
}
