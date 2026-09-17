public class ImportantQuestion extends Resource {
    private static final long serialVersionUID = 1L;
    private String questions;

    public ImportantQuestion(int id, String subject, String exam, String slotGroup,
                             String semester, String year, String uploadedBy,
                             String title, String description, String questions) {
        super(id, subject, exam, slotGroup, semester, year, uploadedBy, title, description);
        this.questions = questions;
    }

    @Override
    public String getType() { return "Important Questions"; }

    @Override
    public void display() {
        super.display();
        System.out.println("Questions   : " + questions);
    }

    @Override
    public String toDataString() {
        return super.toDataString() + "|" + clean(questions);
    }
}
