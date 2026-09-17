import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String registrationNumber;
    private String branch;
    private String semester;
    private String password;

    public Student(String name, String registrationNumber, String branch,
                   String semester, String password) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.branch = branch;
        this.semester = semester;
        this.password = password;
    }

    public String getName() { return name; }
    public String getRegistrationNumber() { return registrationNumber; }
    public String getBranch() { return branch; }
    public String getSemester() { return semester; }

    public boolean checkPassword(String p) {
        return password.equals(p);
    }

    public String toDataString() {
        return name.replace("|","/") + "|" +
               registrationNumber.replace("|","/") + "|" +
               branch.replace("|","/") + "|" +
               semester.replace("|","/") + "|" +
               password.replace("|","/");
    }

    public void display() {
        System.out.println("\n--- Student Profile ---");
        System.out.println("Name         : " + name);
        System.out.println("Registration : " + registrationNumber);
        System.out.println("Branch       : " + branch);
        System.out.println("Semester     : " + semester);
    }
}
