package laynekm.commentparser;

import junit.framework.TestCase;

public class ParserTest extends TestCase {
    public void testJava() {
        Parser parser = new Parser("//", "/*", "*/");
        ParseResult result = parser.parse("test_files/java_test.java");
        assertEquals(18, result.getLines());
        assertEquals(9, result.getCommentLines());
        assertEquals(3, result.getSingleLineComments());
        assertEquals(6, result.getMultiLineComments());
        assertEquals(2, result.getCommentBlocks());
        assertEquals(2, result.getCommentsWithTODOs());
    }
    
    public void testJavascript() {
        Parser parser = new Parser("//", "/*", "*/");
        ParseResult result = parser.parse("test_files/js_test.js");
        assertEquals(13, result.getLines());
        assertEquals(9, result.getCommentLines());
        assertEquals(3, result.getSingleLineComments());
        assertEquals(6, result.getMultiLineComments());
        assertEquals(2, result.getCommentBlocks());
        assertEquals(2, result.getCommentsWithTODOs());
    }
    
    public void testPython() {
        Parser parser = new Parser("#", "'''", "'''");
        ParseResult result = parser.parse("test_files/python_test.py");
        assertEquals(13, result.getLines());
        assertEquals(9, result.getCommentLines());
        assertEquals(3, result.getSingleLineComments());
        assertEquals(6, result.getMultiLineComments());
        assertEquals(2, result.getCommentBlocks());
        assertEquals(2, result.getCommentsWithTODOs());
    }
    
    // SQL only has single line comments (--)
    // Edit: actually, that's not true, but for the sake of this test we'll assume that is the case
    public void testSQL() {
        Parser parser = new Parser("--", null, null);
        ParseResult result = parser.parse("test_files/sql_test.sql");
        assertEquals(3, result.getLines());
        assertEquals(2, result.getCommentLines());
        assertEquals(2, result.getSingleLineComments());
        assertEquals(0, result.getMultiLineComments());
        assertEquals(0, result.getCommentBlocks());
        assertEquals(1, result.getCommentsWithTODOs());
    }
    
    // HTML only has multi line comments (<!-- -->)
    public void testHTML() {
        Parser parser = new Parser(null, "<!--", "-->");
        ParseResult result = parser.parse("test_files/html_test.html");
        assertEquals(13, result.getLines());
        assertEquals(4, result.getCommentLines());
        assertEquals(0, result.getSingleLineComments());
        assertEquals(4, result.getMultiLineComments());
        assertEquals(2, result.getCommentBlocks());
        assertEquals(1, result.getCommentsWithTODOs());
    }
}