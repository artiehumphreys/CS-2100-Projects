import java.util.*;
import java.io.*;
/* program to determine if a file contains matched HTML tags using JCF Stack */
public class HTMLMatch {
    public static boolean match(Scanner input) {
        // to read a single token from a file:
        // String token = input.next();
        Stack<Object> stack = new <Object> Stack();
        while(input.hasNext()){ //while there is data in the html file
            String next = (String) input.next();
            if (next.charAt(0) == '<'){ //checking for tags
                int num = next.indexOf('>');
                String tag = next.substring(0,num+1); //creating strings for the tags to push them
                if (next.charAt(1) != '/') { //only beginning tags
                    stack.push(tag);
                } else { //for end tags
                    String str = (String) stack.peek(); //most recent value in the stack (for finding a match)
                    str = str.substring(0,1) + "/" + str.substring(1); //tag with slash
                    if (next.equals(str)){
                        stack.pop(); //remove it so that the earlier tags can be compared
                    }
                }
            }
        }
        if (stack.empty()) return true; //if every tag had a match
        else return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Program to determine if HTML tags are matched.");
        System.out.println("Enter filename (actual file path)");
        String filename = scan.nextLine();
        //String filename = "Lab6a.html";

        try {
            Scanner inFile = new Scanner(new File(filename));

            if (match(inFile)) {
                System.out.println("Tags are matched in " + filename);
            }
            else
                System.out.println("Tags are not matched in " + filename);

        }
        catch(FileNotFoundException fnf) {
            System.out.println("File not found");
        }
        catch(Exception e) {
            System.out.println("Exception thrown");
        }

    }


}
