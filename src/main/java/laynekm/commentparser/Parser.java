package laynekm.commentparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private String singleLineIdentifier, multiLineStartIdentifier, multiLineEndIdentifier;
    private ArrayList<String> lines;
    private ArrayList<String> commentLines;
    private ArrayList<String> singleLineComments;
    private ArrayList<String> multiLineComments;
    private ArrayList<String> commentsWithTODOs;
    private int blockComments;

    public Parser(String singleLineIdentifier, String multiLineStartIdentifier, String multiLineEndIdentifier) {
        this.singleLineIdentifier = singleLineIdentifier;
        this.multiLineStartIdentifier =multiLineStartIdentifier;
        this.multiLineEndIdentifier = multiLineEndIdentifier;
        this.lines = new ArrayList<String>();
        this.commentLines = new ArrayList<String>();
        this.singleLineComments = new ArrayList<String>();
        this.multiLineComments = new ArrayList<String>();
        this.commentsWithTODOs = new ArrayList<String>();
        this.blockComments = 0;
    }
    
    public ParseResult parse(String filename) {
        // Read file contents into arraylist
        Scanner scanner = new Scanner(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Invalid file!");
            e.printStackTrace();
        }
        
        scanner.close();
        
        // Iterate through lines and extract comments
        // Adds to the commentLines, singleLineComments, and multiLineComments arraylists and iterates blockComments as needed
        boolean insideBlock = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (singleLineIdentifier != null && includesIdentifier(line, singleLineIdentifier)) {
                commentLines.add(line);
                singleLineComments.add(line);
            } else if (multiLineStartIdentifier != null && includesIdentifier(line, multiLineStartIdentifier) && !insideBlock) {
                commentLines.add(line);
                multiLineComments.add(line);
                blockComments++;
                if (!includesIdentifier(line, multiLineEndIdentifier) || multiLineStartIdentifier.equals(multiLineEndIdentifier)) {
                    insideBlock = true;
                }
            } else if (insideBlock) {
                commentLines.add(line);
                multiLineComments.add(line);
                if (includesIdentifier(line, multiLineEndIdentifier)) {
                    insideBlock = false;
                }
            }
        }
        
        // Iterate through comments and extract TODOs
        for (int i = 0; i < commentLines.size(); i++) {
            String line = commentLines.get(i);
            if (line.contains("TODO")) {
                commentsWithTODOs.add(line);
            }
        }
        
        ParseResult result = new ParseResult();
        result.setLines(lines.size());
        result.setCommentLines(commentLines.size());
        result.setSingleLineComments(singleLineComments.size());
        result.setMultiLineComments(multiLineComments.size());
        result.setCommentBlocks(blockComments);
        result.setCommentsWithTODOs(commentsWithTODOs.size());
        return result;
    }
    
    // Returns true if line contains identifier and the identifier is not between quotes
    private boolean includesIdentifier(String line, String identifier) {
        // Special case to account for python comments including quotes
        if (identifier.contains("'")) return line.contains(identifier);
            
        // Regex removes everything between quotes, including the quotes themselves (", ', and `)
        line = line.replaceAll("\".*?\"", "");
        line = line.replaceAll("\'.*?\'", "");
        line = line.replaceAll("\u0060.*?\u0060", "");
        return line.contains(identifier);
    }
}
