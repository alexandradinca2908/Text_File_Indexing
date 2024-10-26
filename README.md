# Application for File Indexing

## Usage:
    For indexing a file, type "index" in the console followed by the absolute
    path for your file

    For searching files containing a certain word, type "query" in the console
    followed by the desired word

    For exiting, just type exit

    For quick testing, you can index the tests directory, then query words
    For deeper testing, you should index file by file

    ANY OTHER COMMAND WILL NOT HAVE AN EFFECT!

##  Description
Main class contains the executable bit, where a while loop runs until it is told
to stop. Valid commands are: index, query, exit. 

Index command requires tokenization, which can be found in FileCheck class.
Tokenization works with the help of a StreamTokenizer, from which only words are selected.
Tokenizing a file means extracting all words as tokens individually, while tokenizing a
directory means recursively traversing it and tokenizing all its contents.

Lastly, we have the global "database" of the app, which is a HashMap containing all indexes;
it is found in Index class

### Note: this app is primitive; from an OOP perspective, it should rely more on encapsulation
