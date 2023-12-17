import java.math.BigInteger;

class CalculatorThread extends Thread {
    private final int start;
    private final int end;
    private BigInteger result;

    public CalculatorThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        result = calculateFactorial(start, end);
    }

    private BigInteger calculateFactorial(int start, int end) {
        BigInteger factorial = BigInteger.ONE;
        for (int i = start; i <= end; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    public BigInteger getResult() {
        return result;
    }
}