import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileManager {
    public static final String DATA_DIR = "data";
    public static final String STUDENTS_FILE = DATA_DIR + File.separator + "students.txt";
    public static final String RESOURCES_FILE = DATA_DIR + File.separator + "resources.txt";
    public static final String UPLOAD_DIR = "uploads";

    public FileManager() {
        new File(DATA_DIR).mkdirs();
        new File(UPLOAD_DIR).mkdirs();
    }

    public List<Student> loadStudents() {
        List<Student> list = new ArrayList<>();
        File f = new File(STUDENTS_FILE);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length >= 5)
                    list.add(new Student(p[0], p[1], p[2], p[3], p[4]));
            }
        } catch (IOException e) {
            System.out.println("Could not load students: " + e.getMessage());
        }
        return list;
    }

    public void saveStudents(List<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student s : students) pw.println(s.toDataString());
        } catch (IOException e) {
            System.out.println("Could not save students: " + e.getMessage());
        }
    }

    public List<Resource> loadResources() {
        List<Resource> list = new ArrayList<>();
        File f = new File(RESOURCES_FILE);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length < 10) continue;

                int id = Integer.parseInt(p[0]);
                String subject=p[1], exam=p[2], slot=p[3], sem=p[4], year=p[5],
                       by=p[6], title=p[7], desc=p[8], extra=p[9];

                if ("Question Paper".equals(extra) && p.length >= 11) {
                    // Legacy-safe: not used for normal records.
                    list.add(new QuestionPaper(id,subject,exam,slot,sem,year,by,title,desc,p[10]));
                } else if (p.length >= 11) {
                    String type = extra;
                    String value = p[10];
                    if ("Important Questions".equals(type))
                        list.add(new ImportantQuestion(id,subject,exam,slot,sem,year,by,title,desc,value));
                    else if ("Important Topics".equals(type))
                        list.add(new ImportantTopic(id,subject,exam,slot,sem,year,by,title,desc,value));
                    else if ("Teacher Instruction".equals(type))
                        list.add(new TeacherInstruction(id,subject,exam,slot,sem,year,by,title,desc,value));
                }
            }
        } catch (Exception e) {
            System.out.println("Could not load resources: " + e.getMessage());
        }
        return list;
    }

    public void saveResources(List<Resource> resources) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RESOURCES_FILE))) {
            for (Resource r : resources) {
                String type = r.getType().replace("|","/");
                String base = r.toDataString();
                if (r instanceof QuestionPaper) {
                    pw.println(base + "|" + type + "|" +
                               ((QuestionPaper) r).getFileName().replace("|","/"));
                } else if (r instanceof ImportantQuestion) {
                    // toDataString already includes its value; remove ambiguity by reconstructing.
                    ImportantQuestion q = (ImportantQuestion) r;
                    pw.println(base.substring(0, base.lastIndexOf("|")) + "|" + type + "|" +
                               base.substring(base.lastIndexOf("|") + 1));
                } else if (r instanceof ImportantTopic) {
                    ImportantTopic t = (ImportantTopic) r;
                    pw.println(base.substring(0, base.lastIndexOf("|")) + "|" + type + "|" +
                               base.substring(base.lastIndexOf("|") + 1));
                } else if (r instanceof TeacherInstruction) {
                    pw.println(base.substring(0, base.lastIndexOf("|")) + "|" + type + "|" +
                               base.substring(base.lastIndexOf("|") + 1));
                }
            }
        } catch (IOException e) {
            System.out.println("Could not save resources: " + e.getMessage());
        }
    }

    public String copyUpload(String sourcePath, int id) throws IOException {
        Path source = Paths.get(sourcePath);
        if (!Files.exists(source) || !Files.isRegularFile(source))
            throw new FileNotFoundException("File does not exist.");

        String original = source.getFileName().toString();
        String safeName = id + "_" + original.replaceAll("[^a-zA-Z0-9._-]", "_");
        Path target = Paths.get(UPLOAD_DIR, safeName);
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        return target.toString();
    }
}
