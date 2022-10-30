
/**
 * @Name    Michael Nguyen
 * @Course  COSC 2436
 * @ProgSet 2
 * @Description Write a program given a string containing only "(", ")" and blanks (spaces), 
 *      compute its complexity. Complexity is defined as follows:
 * 
 *          • Each innermost set of parentheses adds 1 to the total complexity.
 *          • Each set of parentheses containing only innermost set of parentheses adds 2 to 
 *          the total complexity, i.e., one level out will add 2 to the complexity.
 *          • Each set of parentheses at the next level out adds 3 to the complexity, and so on.
 */

import java.util.*;
import java.io.*;

public class ParenLevelsMN {

    /**
     * @name Node
     * @category Class
     * @description class that contains a level and a pointer to next Node
     */
    public static class Node {
        int level;
        Node next;

        /**
         * @name Node
         * @description Constructor for Node class
         * @param level
         */
        public Node(int level) {
            this.level = level;
            this.next = null;
        }

    }

    /**
     * @name StackLL
     * @category Class
     * @description class that contains a top of the stack and a integer for size
     */
    public static class StackLL {
        Node top;
        int size;

        /**
         * @name StackLL
         * @description This is the constructor for the StackLL class. It initializes
         *              the top of the stack to null and the size to 0
         */
        public StackLL() {
            this.top = null;
            this.size = 0;
        }

        /**
         * @name push
         * @description Pushes a new node onto the stack
         * @param level The level of the node
         */
        public void push(int level) {
            Node newNode = new Node(level);
            if (top == null) {
                top = newNode;
            } else {
                newNode.next = top;
                top = newNode;
            }
            size++;
        }

        /**
         * @name pop
         * @description removes the top node from the stack
         * @return the top node's level
         */
        public int pop() {
            if (top == null) {
                return -1;
            } else {
                int level = top.level;
                top = top.next;
                size--;
                return level;
            }
        }

        /**
         * @name peek
         * @return top level
         * @description returns the value at the top of the stack
         */
        public int peek() {
            if (top == null) {
                return -1;
            } else {
                return top.level;
            }
        }

        /**
         * @name isEmpty
         * @return true if stack is empty, false otherwise
         * @description checks to see if the stack is empty stack
         */
        public boolean isEmpty() {
            return top == null;
        }

        /**
         * @name size
         * @return The size of the list.
         * @description Return the number of elements in the list.
         */
        public int size() {
            return size;
        }
    }

    /**
     * @name ParenLevelsMN
     * @description This is the main method for the ParenLevelsMN class. It reads in
     *              the input string and stores it into the stack through push and
     *              pop methods and then displays the complexity
     * @param args
     */
    public static void main(String[] args) {

        // Var to store answer to continue
        char answer = 'y';

        while (answer == 'y' || answer == 'Y') {
            
            // Keep track of the parenthesis left(parenIn) and right(parenOut)
            int parenIn = 0;
            int parenOut = 0;

            // Create a Scanner object to read in the expression
            Scanner input = new Scanner(System.in);

            // Prompt the user to enter an expression
            System.out.println("\nEnter an expression: ");

            // Read in the expression and store in a string
            String expression = input.nextLine();

            // Create a StackLL object to push and pop the parentheses onto
            StackLL stack = new StackLL();

            int complexity = 0;
            int level = 0;
            for (int i = 0; i < expression.length(); i++) {
                if (expression.charAt(i) == '(') {
                    stack.push(level);
                    parenIn++;
                    level++;
                } else if (expression.charAt(i) == ')') {
                    complexity += stack.pop() + 1;
                    parenOut++;
                    level--;
                }
            }
            if (parenIn != parenOut) {
                System.out.println("Parentheses do not match.");
            } else {
                System.out.println("The complexity of the expression is: " + complexity);
            }
            System.out.println("Run Again? (Y/N): ");
            
            answer = input.next().charAt(0);
        }

    }
}
