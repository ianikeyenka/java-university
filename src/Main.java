import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of threads: ");
        int numThreads = scanner.nextInt();
        System.out.print("Enter the start value: ");
        int startValue = scanner.nextInt();
        System.out.print("Enter the end value: ");
        int endValue = scanner.nextInt();
        System.out.print("Enter the operation (add/subtract/multiply): ");
        String operation = scanner.next();

        ThreadGenerator threadGenerator = new ThreadGenerator(numThreads, startValue, endValue, operation);
        threadGenerator.execute();
    }
}