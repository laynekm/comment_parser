package laynekm.commentparser;

// Bean representing the result of parsing a file
public class ParseResult {
    private int lines;
    private int commentLines;
    private int singleLineComments;
    private int multiLineComments;
    private int commentBlocks;
    private int commentsWithTODOs;
    
    // Getters
    public int getLines() { return lines; }
    public int getCommentLines() { return commentLines; }
    public int getSingleLineComments() { return singleLineComments; }
    public int getMultiLineComments() { return multiLineComments; }
    public int getCommentBlocks() { return commentBlocks; }
    public int getCommentsWithTODOs() { return commentsWithTODOs; }
    
    // Setters
    public void setLines(int lines) { this.lines = lines; }
    public void setCommentLines(int commentLines) { this.commentLines = commentLines; }
    public void setSingleLineComments(int singleLineComments) { this.singleLineComments = singleLineComments; }
    public void setMultiLineComments(int multiLineComments) { this.multiLineComments = multiLineComments; }
    public void setCommentBlocks(int commentBlocks) { this.commentBlocks = commentBlocks; }
    public void setCommentsWithTODOs(int commentsWithTODOs) { this.commentsWithTODOs = commentsWithTODOs; }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Total # of lines: " + lines + '\n');
        builder.append("Total # of comment lines: " + commentLines + '\n');
        builder.append("Total # of single line comments: " + singleLineComments + '\n');
        builder.append("Total # of comment lines within block comments: " + multiLineComments + '\n');
        builder.append("Total # of block line comments: " + commentBlocks + '\n');
        builder.append("Total # of TODOs: " + commentsWithTODOs + '\n');
        return builder.toString();
    }
}
