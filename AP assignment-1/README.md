# Assignment 1


## Compilation
### 1) Creating `library-app-1.0-SNAPSHOT.jar` file
The jar file is already created, so this is optional.
To compile the code, first, extract the zip file and then navigate to the directory `demo` by copying the path of the directory demo and typing the command cd "path_of_the_demo_file",
run the command `mvn clean install` to create a .jar file. The Java code is in the file name `App.java`.
### 2) Running the jar file
Open the demo folder, open the target folder, copy the path of the `library-app-1.0-SNAPSHOT.jar` file,
In my case, which was `C:\Users\Shivam\Desktop\Assignment1\demo\target\library-app-1.0-SNAPSHOT.jar`
, then execute the command `java -jar C:\Users\Shivam\Desktop\demo\target\library-app-1.0-SNAPSHOT.jar` where 
`C:\Users\Shivam\Desktop\demo\target\library-app-1.0-SNAPSHOT.jar` is the path of the `library-app-1.0-SNAPSHOT.jar` file.

Or open the terminal in the demo folder(or navigate to the demo directory) and run the command `java -jar target\library-app-1.0-SNAPSHOT.jar`

## About the code
The code executes a Library management system 
To navigate the system, you must enter a number as shown in the menu. Could navigate through the system as a librarian or as a member.
### Assumptions
1. Cannot pay the fine for a book without returning it
2. Phone number is used for logging in by a member and removing a member by the librarian (so it is assumed to be unique)
### Classes
1. Librarian - to keep track of all members and the books
   * register_member - creates a new object of the member class and adds it to the mem_dict to keep track of the members
   * remove_member - remove a member by taking it's phone number
   * add_book - Create new book objects equal to the number of copies of the books and add them to book_dict to keep track of them
   * remove_book - removes a book by taking it's ID if it's not borrowed
   * view_all_members - iterating through the mem_dict hashtable and printing the attributes of the object, calculating the current fine as it gets updated with time
   * view_all_books - iterating through the book_dict and printing the attributes of the object
   * view_all_books_available - Similar to the above method, it prints if the book is not borrowed 
     
2. Member - can borrow the books, return them, and have to pay a fine if late
   * my_books - iterate through the hashset of the member to print the books borrowed by the member
   * elgible_to_issue_book - Check if the member is eligible to issue a book by checking the fine and number of books borrowed by the member
   * issue_book - check if the book is available and set the time at which the book was borrowed to calculate the fine
   * return_book - returns the book and adds a fine if the book was returned late
   * pay_fine - pays the fine if currently issued books do not have any fine; otherwise, you have to return the book to pay the fine
4. Book - stores all information related to the book
   * cuurent_fine - calculate the fine of the books, assuming no fine for 10 seconds from the time the book was issued, then 3 rupees per second 
6. Menu - to navigate through the interface
   * has page attribute and getter and setter methods for it to navigate through the menu pages
### Implementation
It uses hashtables to store the members and hashsets to store the books issued by the member.
