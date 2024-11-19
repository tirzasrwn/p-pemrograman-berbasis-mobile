package id.tirzasrwn.todolist.data

import id.tirzasrwn.todolist.model.Todo

// loadTodos returns a list of todos
class Datasource {
    fun loadTodos(): List<Todo> {
        return listOf(
            Todo(taskName = "Write Python script"),
            Todo(taskName = "Study algorithms"),
            Todo(taskName = "Complete database project"),
            Todo(taskName = "Debug Java application"),
            Todo(taskName = "Prepare for coding interview"),
            Todo(taskName = "Attend software engineering lecture"),
            Todo(taskName = "Work on group project"),
            Todo(taskName = "Submit AI assignment"),
            Todo(taskName = "Practice data structures"),
            Todo(taskName = "Read research paper on machine learning"),
            Todo(taskName = "Contribute to open source project"),
            Todo(taskName = "Test web application"),
            Todo(taskName = "Optimize code performance"),
            Todo(taskName = "Learn new programming language"),
            Todo(taskName = "Develop mobile app prototype"),
            Todo(taskName = "Create GitHub repository"),
            Todo(taskName = "Write technical blog post"),
            Todo(taskName = "Attend hackathon event"),
            Todo(taskName = "Prepare presentation on blockchain"),
            Todo(taskName = "Complete cloud computing tutorial"),
            Todo(taskName = "Analyze cybersecurity vulnerabilities"),
            Todo(taskName = "Build portfolio website"),
            Todo(taskName = "Solve competitive programming problems"),
            Todo(taskName = "Design software architecture"),
            Todo(taskName = "Refactor legacy code"),
            Todo(taskName = "Participate in code review session"),
            Todo(taskName = "Explore DevOps tools"),
            Todo(taskName = "Implement RESTful API"),
            Todo(taskName = "Work on capstone project"),
            Todo(taskName = "Write unit tests for application"),
            Todo(taskName = "Learn about Kubernetes and Docker"),
            Todo(taskName = "Optimize SQL queries"),
            Todo(taskName = "Complete front-end development course"),
            Todo(taskName = "Experiment with neural networks"),
            Todo(taskName = "Simulate network protocols"),
            Todo(taskName = "Learn version control with Git"),
            Todo(taskName = "Prepare final year project report"),
            Todo(taskName = "Design user-friendly interface"),
            Todo(taskName = "Set up virtual environment"),
            Todo(taskName = "Practice solving leetcode problems"),
        )
    }
}