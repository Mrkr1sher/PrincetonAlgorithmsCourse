import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private double[] trialsArr;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be greater than or equal to 1");
        if (trials <= 0)
            throw new IllegalArgumentException("Trials must be greater than or equal to 1");
        Percolation tester;
        trialsArr = new double[trials];
        for (int i = 0; i < trials; i++) {
            tester = new Percolation(n);
            while (!tester.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                tester.open(row, col);
            }
            trialsArr[i] = tester.numberOfOpenSites()/Math.pow(n,2);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialsArr);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialsArr);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev())/Math.sqrt(trialsArr.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev())/Math.sqrt(trialsArr.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats test = new PercolationStats(n, T);
        StdOut.println("mean \t\t = " + test.mean());
        StdOut.println("stddev \t\t = " + test.stddev());
        StdOut.println("95% confidence interval \t\t = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
    }
}