# Project Statement

## Title
**ExamVault – College Exam Resource Sharing System**

## Problem Statement
Students often receive question papers, important questions, important topics, and teacher instructions through informal messaging groups. These resources can become difficult to locate because they are mixed with unrelated messages and are not organized by subject, examination, or slot group.

In a timetable where related slots form groups such as C11/C12/C13, a structured repository can associate resources with the relevant subject, exam, and slot group so students can refer to resources from the same group.

## Scope
ExamVault is a local command-line Java prototype. It supports student authentication, question-paper upload, important-question/topic/instruction sharing, slot-group organization, searching/filtering, ownership-based deletion, and file-based persistence.

## Target Users
College students who want to upload or refer to examination resources.

## Objectives
- Organize exam resources in one place.
- Associate resources with subject, exam, and slot group.
- Support question-paper sharing.
- Support important questions and topics.
- Support teacher instructions.
- Provide search and filtering.
- Demonstrate Java OOP, collections, file handling, validation, and modular design.

## Functional Requirements
1. Register a student.
2. Authenticate a registered student.
3. Upload question papers.
4. Upload important questions.
5. Upload important topics.
6. Upload teacher instructions.
7. Browse resources.
8. Search by subject, exam, slot group, and keyword.
9. View own uploads.
10. Delete only own resources.

## Non-Functional Requirements
See `docs/NonFunctionalRequirements.md` for usability, reliability, maintainability, error handling, resource efficiency, and security considerations.

## High-Level Workflow
Register/Login → Dashboard → Select operation → Enter subject/exam/slot group → Save or search → View results → Logout.

## Expected Outcome
A fully executable command-line Java application demonstrating practical object-oriented programming, file handling, validation, modularity, and resource management.
