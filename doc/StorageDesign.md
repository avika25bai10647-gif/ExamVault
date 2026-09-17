# Storage Design

The application uses local text files so it can run directly from the command line without a database server.

## students.txt
`name | registrationNumber | branch | semester | password`

## resources.txt
`id | type | subject | exam | slotGroup | semester | year | uploadedBy | title | description | extra`

`extra` contains either the copied question-paper path, important questions, important topics, or teacher instruction.

## uploads/
Question-paper files selected by the user are copied here.

## Logical Relationship
```text
STUDENT (registrationNumber)
        |
        | uploads
        v
RESOURCE (id, uploadedBy, subject, exam, slotGroup, type, ...)
```
