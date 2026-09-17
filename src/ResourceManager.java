import java.util.*;

public class ResourceManager {
    private List<Resource> resources;
    private FileManager fileManager;
    private int nextId;

    public ResourceManager(FileManager fm) {
        fileManager = fm;
        resources = fm.loadResources();
        nextId = 1;
        for (Resource r : resources) if (r.getId() >= nextId) nextId = r.getId() + 1;
    }

    public int generateId() { return nextId++; }

    public void add(Resource r) {
        resources.add(r);
        fileManager.saveResources(resources);
    }

    public List<Resource> getAll() {
        return new ArrayList<>(resources);
    }

    public List<Resource> search(String subject, String exam, String slotGroup, String keyword) {
        List<Resource> result = new ArrayList<>();
        for (Resource r : resources) {
            boolean ok = contains(r.getSubject(), subject)
                      && contains(r.getExam(), exam)
                      && contains(r.getSlotGroup(), slotGroup)
                      && (keyword == null || keyword.trim().isEmpty()
                          || contains(r.getTitle(), keyword)
                          || contains(r.getDescription(), keyword)
                          || contains(r.toDataString(), keyword));
            if (ok) result.add(r);
        }
        return result;
    }

    private boolean contains(String value, String query) {
        return query == null || query.trim().isEmpty()
                || value.toLowerCase().contains(query.trim().toLowerCase());
    }

    public boolean delete(int id, String registrationNumber) {
        Iterator<Resource> it = resources.iterator();
        while (it.hasNext()) {
            Resource r = it.next();
            if (r.getId() == id && r.getUploadedBy().equalsIgnoreCase(registrationNumber)) {
                it.remove();
                fileManager.saveResources(resources);
                return true;
            }
        }
        return false;
    }

    public List<Resource> getByUser(String registrationNumber) {
        List<Resource> result = new ArrayList<>();
        for (Resource r : resources)
            if (r.getUploadedBy().equalsIgnoreCase(registrationNumber)) result.add(r);
        return result;
    }
}
