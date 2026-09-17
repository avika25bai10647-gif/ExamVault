# Non-Functional Requirements

1. **Usability:** Clear menus, prompts, and readable command-line output.
2. **Reliability:** Student and resource metadata persist between executions through local files.
3. **Maintainability:** Responsibilities are divided across meaningful Java classes.
4. **Error Handling:** Invalid menu input, duplicate registration, invalid slot selection, missing upload files, and file errors are handled.
5. **Resource Efficiency:** The system uses local text storage and Java collections without a server dependency.
6. **Security Consideration:** Only the uploader can delete their resource. Passwords are plain-text in this academic prototype; production software should use secure hashing.
