package laynekm.commentparser;

public class App {
    
    public static void main( String[] args ) {
        String filename = null, singleLineIdentifier = null, multiLineStartIdentifier = null, multiLineEndIdentifier = null;
        
        if (args.length < 2) {
            System.out.println("Requires at least two arguments.");
            System.exit(-1);
        } else if (args.length == 2) {
            filename = args[0];
            singleLineIdentifier = args[1].substring(1, args[1].length() - 1);
        } else if (args.length == 3) {
            filename = args[0];
            multiLineStartIdentifier = args[1].substring(1, args[1].length() - 1);
            multiLineEndIdentifier = args[2].substring(1, args[2].length() - 1);
        } else {
            filename = args[0];
            singleLineIdentifier = args[1].substring(1, args[1].length() - 1);
            multiLineStartIdentifier = args[2].substring(1, args[2].length() - 1);
            multiLineEndIdentifier = args[3].substring(1, args[3].length() - 1);
        }
        
        Parser parser = new Parser(singleLineIdentifier, multiLineStartIdentifier, multiLineEndIdentifier);
        ParseResult result = parser.parse(filename);
        System.out.println(result.toString());
        System.exit(0);
    }
}
