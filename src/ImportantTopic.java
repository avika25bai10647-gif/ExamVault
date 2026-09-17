public class ImportantTopic extends Resource {
    private static final long serialVersionUID = 1L;
    private String topics;

    public ImportantTopic(int id, String subject, String exam, String slotGroup,
                          String semester, String year, String uploadedBy,
                          String title, String description, String topics) {
        super(id, subject, exam, slotGroup, semester, year, uploadedBy, title, description);
        this.topics = topics;
    }

    @Override
    public String getType() { return "Important Topics"; }

    @Override
    public void display() {
        super.display();
        System.out.println("Topics      : " + topics);
    }

    @Override
    public String toDataString() {
        return super.toDataString() + "|" + clean(topics);
    }
}
