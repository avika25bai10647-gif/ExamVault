public class TeacherInstruction extends Resource {
    private static final long serialVersionUID = 1L;
    private String instruction;

    public TeacherInstruction(int id, String subject, String exam, String slotGroup,
                              String semester, String year, String uploadedBy,
                              String title, String description, String instruction) {
        super(id, subject, exam, slotGroup, semester, year, uploadedBy, title, description);
        this.instruction = instruction;
    }

    @Override
    public String getType() { return "Teacher Instruction"; }

    @Override
    public void display() {
        super.display();
        System.out.println("Instruction : " + instruction);
    }

    @Override
    public String toDataString() {
        return super.toDataString() + "|" + clean(instruction);
    }
}
