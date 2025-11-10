Library Manager
===

This simple program allows the user to manage a personal library.
User can add, search, borrow and return books, as well as view
the entire collection.

**Java** version 18 or higher and **H2** version 2.2 or higher 
is needed to run this program.

- **Adding Books**: User provides the book's title, author, ISBN 
and the publishing year to add it to the library, the program then
automatically assigns the book with a status *Available*
- **Search Books**: Enter either book's title or author and find
whether that book exists inside the library
- **Borrow Books**: First search for a book, if it's in the library
and is *Available*, user can choose whether they want to borrow it,
this changes the status of the book to *Borrowed*
- **Return Books**: Again search for a book, if it's in the library
and is *Borrowed*, user can choose if they want to return it, this
returns the status back to *Available*
- **Printout Library**: Has the program display every book in the
library, showing the title, author, ISBN, publishing year and its
availability status
```
// Adding a book to the library
Choose an option (Add, Search, Borrow, Return, Printout): add
Enter book title: The Best Book
Enter book author: Best Author
Enter book ISBN: 202202
Enter the book's publication year: 1999
Book added successfully!

// Printing out entire library
Would you like to continue? (yes/no) yes
Choose an option (Add, Search, Borrow, Return, Printout): printout
The library contains the following books:
Title: The Best Book | Author: Best Author | Year published: 1999 | ISBN: 202202| Availabilty: Available
Would you like to continue? (yes/no) no
The program has been terminated.
```

To run the program, first make sure Java 18 or higher and H2 2.2 or
higher is installed.
Then, open the Command prompt window and navigate to the folder that
contains the "Target" folder using the **cd** command.
Example in Command Prompt:

```cd C:\Users\%NAME%\IdeaProjects\LibraryManager```

Now you have to run the .jar file inside the Target folder:

```java -jar target/LibraryManager.jar```

This will start the program. Now simply follow the instructions 
that the program provides.