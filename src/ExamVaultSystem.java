import java.util.*;

public class ExamVaultSystem {
    private final Scanner sc = new Scanner(System.in);
    private final FileManager fileManager = new FileManager();
    private final List<Student> students;
    private final ResourceManager resourceManager;
    private Student currentUser;

    private final String[] slotGroups = {
        "A11/A12/A13", "B11/B12/B13", "C11/C12/C13",
        "D11/D12/D13", "E11/E12/E13", "F11/F12/F13"
    };

    public ExamVaultSystem() {
        students = fileManager.loadStudents();
        resourceManager = new ResourceManager(fileManager);
    }

    public void start() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("              EXAMVAULT");
            System.out.println("   College Exam Resource Repository");
            System.out.println("========================================");
            System.out.println("1. Register\n2. Login\n3. Exit");
            int choice = readInt("Enter choice: ");
            if (choice == 1) register();
            else if (choice == 2 && login()) dashboard();
            else if (choice == 3) { System.out.println("Thank you for using ExamVault!"); return; }
            else if (choice != 2) System.out.println("Invalid choice.");
        }
    }

    private void register() {
        System.out.println("\n--- Student Registration ---");
        String name = required("Name: ");
        String reg = required("Registration Number: ");
        for (Student s : students) if (s.getRegistrationNumber().equalsIgnoreCase(reg)) {
            System.out.println("Registration number already exists."); return;
        }
        String branch = required("Branch: ");
        String semester = required("Semester: ");
        String password = required("Password: ");
        students.add(new Student(name, reg, branch, semester, password));
        fileManager.saveStudents(students);
        System.out.println("Registration successful.");
    }

    private boolean login() {
        System.out.println("\n--- Login ---");
        String reg = required("Registration Number: ");
        String password = required("Password: ");
        for (Student s : students) {
            if (s.getRegistrationNumber().equalsIgnoreCase(reg) && s.checkPassword(password)) {
                currentUser = s;
                System.out.println("Login successful. Welcome, " + s.getName() + "!");
                return true;
            }
        }
        System.out.println("Invalid registration number or password.");
        return false;
    }

    private void dashboard() {
        while (currentUser != null) {
            System.out.println("\n========================================");
            System.out.println("              DASHBOARD");
            System.out.println("========================================");
            System.out.println("Welcome, " + currentUser.getName());
            System.out.println("1. Browse All Resources");
            System.out.println("2. Search Resources");
            System.out.println("3. Upload Question Paper");
            System.out.println("4. Upload Important Questions");
            System.out.println("5. Upload Important Topics");
            System.out.println("6. Upload Teacher Instruction");
            System.out.println("7. My Uploads");
            System.out.println("8. Delete My Upload");
            System.out.println("9. View Profile");
            System.out.println("10. Logout");
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1: show(resourceManager.getAll(), "ALL RESOURCES"); break;
                case 2: search(); break;
                case 3: uploadPaper(); break;
                case 4: uploadText("Important Questions"); break;
                case 5: uploadText("Important Topics"); break;
                case 6: uploadText("Teacher Instruction"); break;
                case 7: show(resourceManager.getByUser(currentUser.getRegistrationNumber()), "MY UPLOADS"); break;
                case 8: deleteUpload(); break;
                case 9: currentUser.display(); break;
                case 10: currentUser = null; System.out.println("Logged out."); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void search() {
        System.out.println("\n--- Search Resources ---");
        String subject = read("Subject (blank = any): ");
        String exam = read("Exam (CAT-1/CAT-2/TEE, blank = any): ");
        String slot = read("Slot Group (e.g. C11/C12/C13, blank = any): ");
        String keyword = read("Keyword (blank = any): ");
        show(resourceManager.search(subject, exam, slot, keyword), "SEARCH RESULTS");
    }

    private String[] details() {
        String subject = required("Subject: ");
        String exam = required("Exam (CAT-1/CAT-2/TEE): ");
        String slot = chooseSlotGroup();
        if (slot == null) return null;
        String semester = required("Semester: ");
        String year = required("Academic Year: ");
        String title = required("Title: ");
        String description = read("Description (optional): ");
        return new String[]{subject, exam, slot, semester, year, title, description};
    }

    private String chooseSlotGroup() {
        System.out.println("\nAvailable Slot Groups:");
        for (int i = 0; i < slotGroups.length; i++) System.out.println((i + 1) + ". " + slotGroups[i]);
        int choice = readInt("Select slot group: ");
        if (choice < 1 || choice > slotGroups.length) { System.out.println("Invalid slot group."); return null; }
        return slotGroups[choice - 1];
    }

    private void uploadPaper() {
        System.out.println("\n--- Upload Question Paper ---");
        String[] d = details(); if (d == null) return;
        String path = required("Full path of question paper file: ");
        int id = resourceManager.generateId();
        try {
            String stored = fileManager.copyUpload(path, id);
            resourceManager.add(new QuestionPaper(id,d[0],d[1],d[2],d[3],d[4],currentUser.getRegistrationNumber(),d[5],d[6],stored));
            System.out.println("Question paper uploaded successfully.");
        } catch (Exception e) { System.out.println("Upload failed: " + e.getMessage()); }
    }

    private void uploadText(String type) {
        System.out.println("\n--- Upload " + type + " ---");
        String[] d = details(); if (d == null) return;
        String content = required(type.equals("Important Questions") ? "Important questions: " : type.equals("Important Topics") ? "Important topics: " : "Teacher instruction: ");
        int id = resourceManager.generateId();
        Resource r;
        if (type.equals("Important Questions")) r = new ImportantQuestion(id,d[0],d[1],d[2],d[3],d[4],currentUser.getRegistrationNumber(),d[5],d[6],content);
        else if (type.equals("Important Topics")) r = new ImportantTopic(id,d[0],d[1],d[2],d[3],d[4],currentUser.getRegistrationNumber(),d[5],d[6],content);
        else r = new TeacherInstruction(id,d[0],d[1],d[2],d[3],d[4],currentUser.getRegistrationNumber(),d[5],d[6],content);
        resourceManager.add(r);
        System.out.println(type + " uploaded successfully.");
    }

    private void deleteUpload() {
        show(resourceManager.getByUser(currentUser.getRegistrationNumber()), "MY UPLOADS");
        int id = readInt("Enter Resource ID to delete: ");
        System.out.println(resourceManager.delete(id, currentUser.getRegistrationNumber()) ? "Resource deleted successfully." : "Resource not found or it is not your upload.");
    }

    private void show(List<Resource> list, String heading) {
        System.out.println("\n========== " + heading + " ==========");
        if (list.isEmpty()) { System.out.println("No resources found."); return; }
        for (Resource r : list) r.display();
    }

    private String required(String prompt) {
        while (true) { String v = read(prompt); if (!v.isEmpty()) return v; System.out.println("This field cannot be empty."); }
    }
    private String read(String prompt) { System.out.print(prompt); return sc.nextLine().trim(); }
    private int readInt(String prompt) {
        while (true) try { return Integer.parseInt(read(prompt)); } catch (NumberFormatException e) { System.out.println("Please enter a valid number."); }
    }
}
