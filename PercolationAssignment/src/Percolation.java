import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {

    private boolean[] opened;
    private int opens;
    private int n;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        this.n = n;
        uf = new WeightedQuickUnionUF(n*n + 2);
        opened = new boolean[n * n + 2];
        for (int i = 1; i <= n; i++)
            uf.union(0, i);
        for (int i = (n*n-n) + 1; i <= n*n; i++)
            uf.union(n*n+1, i);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException("Invalid Range: " + row + ", " + col);
        if (isOpen(row, col)) return;
        int spot = (row - 1) * n + col;
        opened[spot] = true;
        opens++;
        if (col < n && isOpen(row, col+1))
            uf.union(spot, spot+1);
        if (col > 1 && isOpen(row, col-1))
            uf.union(spot, spot-1);
        if (row > 1 && isOpen(row-1, col))
            uf.union(spot, spot-n);
        if (row < n && isOpen(row+1, col))
            uf.union(spot, spot+n);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) throw new IllegalArgumentException("Invalid Range: " + row + ", " + col);
        int spot = (row - 1) * n + col;
        return opened[spot];

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return !isOpen(row, col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opens;

    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find( n*n+1);

    }
}