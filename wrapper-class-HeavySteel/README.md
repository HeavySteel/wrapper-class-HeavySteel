[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/S7srhyG_)
# Assignment

This assignment will allow you to demonstrate your knowledge of the following topics:

* Record classes  
* Wrapper classes  
* HashMap generic class  
* Interfaces  
* JavaDoc  
* Builder classes  
* `var` declarations  

---

## Project Manager Statement

As your Java instructor, I need to evaluate your ability to create a wrapper class that functions as a database for storing and managing user information.

---

## Acceptance Criteria

To receive full credit for this assignment, you must meet the following acceptance criteria:

* All required files and packages have already been created.
* DO NOT EDIT UNIT TESTS.
* The interface `IDatabase` must not be altered.  
* You need to implement the appropriate Java code in `Database.java` and `UserInfo.java`.  

### `UserInfo.java`
* Create a record class that includes the ability to add notes (Strings) as list entries.  
* Implement a builder class for the record class.  
* Refer to the main driver class for guidance on the required methods and fields.
* Certain input validations need to be applied
  * Some months have 30 days
  * Some months have 28 or 29 days (depending on leap year)
  * Some months have 31 days
  * BirthYear needs to be greater than or equal to 1900
  * BirthYear needs to be less than or equal to 2024
  * BirthMonth must be greater than zero
  * BirthMonth must be less than or equal to 12
  * UserID must be an integer greater than 0
  * Leap years are always multiples of 4
  * There may be a class you can utilize to help with validations 

### `Database.java`
* Implement a `HashMap` to store `UserInfo` instances keyed by `Integer` (userId).  
* Ensure the class implements the `IDatabase` interface.  
* Incorporate the code/string responses defined in the enum class `Responses`.  
* Refer to the main driver class for guidance.

---

## Grading Criteria

This assignment is worth 100 points. Below is a breakdown of how points will be awarded:

* **10 points**: Accepting the assignment  
* **20 points**: Making the first commit  
* **70 points**: Passing all unit tests on the GitHub workflow autograder  

No partial credit will be awarded for any of the categories listed above. As a result, there are only four possible grades for this assignment:

* **0 points**: Assignment was not accepted.  
* **10 points**: Assignment was accepted.  
* **30 points**: Assignment was accepted, and a commit was pushed to the repository.  
* **100 points**: Assignment was accepted, commits were pushed to the GitHub repository, and all GitHub workflow autograder unit tests were passed (green checkmark).  
