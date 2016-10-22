import java.util.*;

public class GoogleTest {

    // flag to continue testing from user input
    public static Boolean continueTesting = true;

    public static void main(String[] args) {
//        //instantiate the bound
//        int bound = 3;
//        //instantiate the buckets
//        int[] buckets = {1, 1, 2, 2, 2};
//        //instantiate a BucketWeigher
//        BucketWeigher weigher = new BucketWeigher(buckets);
//        //try to calculate the bucket weights
//        int[] weights = weigher.findWeights(bound);
//        System.out.println("weights before: " + arrayToString(buckets));
//        System.out.println("weights after: " + arrayToString(weights));

        if (continueTesting) {
            Scanner scn = new Scanner(System.in);
            testIsBalanced(scn);
        }
        System.out.println("Thanks for testing!");

    }

    public static void testIsBalanced(Scanner scn) {
        System.out.print("Enter a delimiter string to check balancing on \n(consisting of characters [,],(,),{,}): ");
        String delimStr = scn.nextLine();
        // assume user input is correct
        Boolean balanced = isBalanced(delimStr);
        System.out.println("\n is input string " + delimStr + " balanced? \t" + balanced);
        System.out.print("Would you like to enter another test? (y, n): ");
        String decision = scn.nextLine();
        // ask the user if they want to try again
        System.out.println("scn.nextLine(): " + decision);
        while (!decision.equals("y") && !decision.equals("n")) {
            System.out.println("That is not a valid decision!");
            System.out.print("Would you like to enter another test? (y, n): ");
            decision = scn.nextLine();
        }

        continueTesting = decision.equals("y");
    }

    /**
     * Checks if the given delimited string is balanced
     * @param delimStr String containing delimiters assumes only characters [,],(,),{,} are used
     * @return true if the delimited string is balanced
     */
    public static Boolean isBalanced(String delimStr) {
        System.out.println("delimStr: " + delimStr);
        // we know odd-length strings are not delimited
        if (delimStr.length() % 2 != 0) return false;
        // instantiate a stack to hold the string
        Stack<Character> stack = new Stack<Character>();
        // instantiate a dictionary to hold the inverse delimiters
        HashMap<Character, Character> dict = new HashMap<Character, Character>();
        dict.put('[', ']');
        dict.put('{', '}');
        dict.put('(', ')');

        // push the first delimiter
        stack.push(delimStr.charAt(0));// we cheat a little :]
        // traverse the delimStr pushing and popping delimiters as we match them
        for (int i = 1; i < delimStr.length(); i++) {
            // check to see if the delimiters match
            if (stack.size() > 0 &&
                    delimStr.charAt(i) == dict.get(stack.peek()))
                stack.pop();// pop the character
            else if (dict.containsKey(delimStr.charAt(i)))// we can push the character
                stack.push(delimStr.charAt(i));
            else {// failure case
                return false;
            }
        }
        // if the stack still contains delimiters the delimStr was not balanced
        if (stack.size() > 0)
            return false;
        // the stack was empty so we know the delimStr was balanced
        return true;
    }

    public static String arrayToString(int[] arr) {
        String str = "[" + arr[0];
        for (int i = 1; i < arr.length; i++) {
            str += ", "  + arr[i];
        }
        str += "]";
        return str;
    }


}

class BucketWeigher {
    private int[] buckets;

    public BucketWeigher(int[] buckets) {
        this.buckets = buckets;
    }

    /**
     * Finds the value of the weights in each bucket
     * and returns an array with their values.
     *
     * @param k Maximum weight bound + 1
     * @return An array containing the weight of a weight for
     * each bucket. Only one weighing is allowed
     */
    public int[] findWeights(int k) {
        //get the length of buckets
        int n = buckets.length;
        //instantiate a place to hold the final weights
        int[] weights = new int[n];
        //get the base k map sum of the given buckets
        int sum = baseMapSum(buckets, k);
        //mod the base k map sum by the power of k^i to get the weight at each index
        for(int i = 1; i < n; i++) {
            weights[i] = (int) (sum / Math.pow(k, i));
            System.out.println(sum);
        }
        return weights;
    }

    public int baseMapSum(int[] A, int k) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i] & 0xff;
        }
        System.out.println("sum: " + sum);
        return sum;
    }
}
