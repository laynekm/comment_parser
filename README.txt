This program determines the amount of lines, comments, and TODOs in a file, independent of language.
It makes sure to ignore "comments" that are actually in quotes, so only real comments are counted.

INSTRUCTIONS
This is a Maven project so it should be imported to your IDE as such. Requires Java 1.7 at minimum.
In most cases you will want to include four launch arguments, dependent on the type of file you are parsing:
	1. The name of the file
	2. The single line comment identifier (ex. '//')
	3. The multi line comment start identifier (ex. '/*')
	4. The multi line comment end identifier (ex. '*/')
	
For languages that only include single line comments, arguments 3 and 4 can be ignored.
For languages that only include multi line comments, set the start/end identifiers as arguments 2 and 3.

IMPORTANT: Identifier arguments MUST be wrapped in single quotes (ex. '//'). This is due to Java 
handling arguments like "/*" as commands themselves.

EXAMPLE LAUNCH ARGUMENTS
test_files/java_test.java '//' '/*' '*/'

I figured it was better to allow the user to input the comment identifiers themselves rather than hardcoding 
them into the app and choosing them based on the file extension.
This makes the program more verbose since there's so many different languages out there.

I've included test files and JUnit test cases for Java, Javascript, Python, SQL, and HTML files.
