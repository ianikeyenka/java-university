import java.math.BigInteger;

class ThreadGenerator {
    private final int numThreads;
    private final int startValue;
    private final int endValue;
    private final String operation;

    public ThreadGenerator(int numThreads, int startValue, int endValue, String operation) {
        this.numThreads = numThreads;
        this.startValue = startValue;
        this.endValue = endValue;
        this.operation = operation;
    }

    public void execute() {
        CalculatorThread[] threads = new CalculatorThread[numThreads];
        int range = (endValue - startValue + 1) / numThreads;
        int remaining = (endValue - startValue + 1) % numThreads;
        int currentStart = startValue;

        BigInteger finalResult = BigInteger.ONE;

        for (int i = 0; i < numThreads; i++) {
            int currentEnd = currentStart + range - 1;
            if (remaining > 0) {
                currentEnd++;
                remaining--;
            }

            CalculatorThread thread = new CalculatorThread(currentStart, currentEnd);
            threads[i] = thread;
            thread.start();

            currentStart = currentEnd + 1;
        }

        try {
            for (CalculatorThread thread : threads) {
                thread.join();
                BigInteger threadResult = thread.getResult();
                switch (operation) {
                    case "add":
                        finalResult = finalResult.add(threadResult);
                        break;
                    case "subtract":
                        finalResult = finalResult.subtract(threadResult);
                        break;
                    case "multiply":
                        finalResult = finalResult.multiply(threadResult);
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final result: " + finalResult);
    }
}