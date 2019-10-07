# Query-Language

To run the program in your command prompt type -
> javac CmdInterpretor.java
> java CmdInterpretor


The commands in the query language work the following way :-

// CREATE ------------
● "create" "table" <table name> "(" <column name>,
+
 ")" ";" Creates an empty table with
the given name (replacing it if it is already loaded). The names of its columns are given
by the column names in order. There must not be any duplicate column names.

// LOAD --------------
● "load" <name> ";" Load data from the file name.db to create a table named name.

// STORE ------------
● "store" <table name> ";" Store the data from the table table name into the file table
name.db.

// INSERT ------------
● "insert" "into" <table name> "values" ( "(" <literal>,
+
 ")" )
,
+
 ";" Adds new rows to the
given table whose values are given by the parenthesized lists of literals. In each
parenthesized list, there must be exactly one literal for each column of the table, and
the table must already exist. Rows are not added if they duplicate a row already in the
table.

// PRINT ------------
● "print" <table name> ";" Print all rows of the table with the given name (which must be
loaded). The rows are printed one per line and indented. Separate columns with blanks,
and print the columns in the order they were specified when the table was created.

// QUIT ------------
● "quit" ";" Exit the program.

// EXIT -------------
● "exit" ";" Synonym for quit.


