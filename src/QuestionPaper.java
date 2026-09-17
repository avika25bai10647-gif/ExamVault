public class QuestionPaper extends Resource {
    private static final long serialVersionUID = 1L;
    private String fileName;

    public QuestionPaper(int id, String subject, String exam, String slotGroup,
                         String semester, String year, String uploadedBy,
                         String title, String description, String fileName) {
        super(id, subject, exam, slotGroup, semester, year, uploadedBy, title, description);
        this.fileName = fileName;
    }

    @Override
    public String getType() { return "Question Paper"; }

    public String getFileName() { return fileName; }

    @Override
    public void display() {
        super.display();
        System.out.println("File        : " + fileName);
    }
}
