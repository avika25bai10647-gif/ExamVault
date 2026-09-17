import java.io.Serializable;

public abstract class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int id;
    protected String subject;
    protected String exam;
    protected String slotGroup;
    protected String semester;
    protected String year;
    protected String uploadedBy;
    protected String title;
    protected String description;

    public Resource(int id, String subject, String exam, String slotGroup,
                    String semester, String year, String uploadedBy,
                    String title, String description) {
        this.id = id;
        this.subject = subject;
        this.exam = exam;
        this.slotGroup = slotGroup;
        this.semester = semester;
        this.year = year;
        this.uploadedBy = uploadedBy;
        this.title = title;
        this.description = description;
    }

    public abstract String getType();

    public int getId() { return id; }
    public String getSubject() { return subject; }
    public String getExam() { return exam; }
    public String getSlotGroup() { return slotGroup; }
    public String getSemester() { return semester; }
    public String getYear() { return year; }
    public String getUploadedBy() { return uploadedBy; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }

    public String toDataString() {
        return id + "|" + clean(subject) + "|" + clean(exam) + "|" +
               clean(slotGroup) + "|" + clean(semester) + "|" + clean(year) + "|" +
               clean(uploadedBy) + "|" + clean(title) + "|" + clean(description);
    }

    protected String clean(String s) {
        return s == null ? "" : s.replace("|", "/").replace("\n", " ");
    }

    public void display() {
        System.out.println("\n[" + id + "] " + getType());
        System.out.println("Title       : " + title);
        System.out.println("Subject     : " + subject);
        System.out.println("Exam        : " + exam);
        System.out.println("Slot Group  : " + slotGroup);
        System.out.println("Semester    : " + semester);
        System.out.println("Year        : " + year);
        System.out.println("Uploaded By : " + uploadedBy);
        if (!description.isEmpty()) System.out.println("Details     : " + description);
    }
}
