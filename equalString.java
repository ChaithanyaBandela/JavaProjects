import java.util.Scanner;

public class equalString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of test cases
        int T = sc.nextInt();
        sc.nextLine(); // Consume the newline

        // Process each test case
        for (int t = 0; t < T; t++) {
            // Read the length of the binary string
            int N = sc.nextInt();
            sc.nextLine(); // Consume the newline

            // Read the binary string
            String S = sc.nextLine();

            // Initialize an empty result string
            String result = "";

            // Iterate through the binary string in pairs
            for (int i = 0; i < N; i += 2) {
                char first = S.charAt(i);
                char second = S.charAt(i + 1);

                // Determine the DNA character based on the binary pair
                if (first == '0' && second == '0') {
                    result += "A";
                } else if (first == '0' && second == '1') {
                    result += "T";
                } else if (first == '1' && second == '0') {
                    result += "C";
                } else if (first == '1' && second == '1') {
                    result += "G";
                }
            }

            // Print the result for the current test case
            System.out.println(result);
        }

        sc.close();
    }
}
