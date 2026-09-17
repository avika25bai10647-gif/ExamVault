# Sequence Diagram – Search Resource

```mermaid
sequenceDiagram
    actor Student
    participant System as ExamVaultSystem
    participant Search as SearchManager
    participant Manager as ResourceManager

    Student->>System: Enter subject, exam, slot group, keyword
    System->>Search: searchAndDisplay(...)
    Search->>Manager: search(...)
    Manager-->>Search: Matching Resource List
    Search-->>System: Display results
    System-->>Student: Show matching resources
```
