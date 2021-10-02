# EA coding test

### How to run this application
1. In Intellij
   1. Open the project in Intellij
   2. Navigate to the Maven configurations
   3. Double click *package*. This generates a jar file located under the *target* folder
   4. Run the jar file from Intellij's contextual menu.
2. In Eclipse
   1. TBD.
3. In command line (linux/macos)
   1. In the root of this project, run **mvn package**
   2. Locate the resulting jar file under *targets*
   3. Run **java -jar PalindromeGame-0.0.1SNAPSHOT.jar**

### How to test this application
1. In Postman
2. In command line

### Assumptions
1. Score is 0 if:
   1. If a word s not a palindrome.
   2. If a word is comprise of only one (1) alphanumeric character.
2. Capitalization for the username and/or the word is not relevant.
3. English only.
4. Score is the floor of the division by 2.
5. Scores are whole numbers (Integers).

For further reference, please consider the following sections:

