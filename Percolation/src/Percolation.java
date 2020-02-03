public class Percolation {

    private int grid[];
    private int sz[];
    private boolean[] opened;
    private int opens;
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        if (n <= 0) throw new IllegalArgumentException("n must be greater than 0");
        grid = new int[n*n+2];
        opened = new boolean[n*n+2];
        sz = new int[n*n+2];
        grid[1+n*n] = 1+n*n;
        sz[1+n*n] = 1;

        for (int i = 0; i < n*n+1; i++) {
            grid[i] = i;
            opened[i] = false;
            sz[i] = 1;
            if (i <= n) {
                union(grid[0], grid[i]);
            }
            if (i > n*n-n) {
                union(grid[1+n*n], grid[i]);
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException("Invalid Range");
        if (isOpen(row, col)) return;
        int spot = (row - 1) * n + col;
        opened[spot] = true;
        opens++;
        if (spot%n != 0)
            if (opened[spot+1]) union(spot, spot+1);
        if (spot%n != 1)
            if (opened[spot-1]) union(spot, spot-1);
        if (spot+n < grid.length)
            if (opened[spot+n]) union(spot, spot+n);
        if (spot-n > 0)
            if (opened[spot-n]) union(spot, spot - n);

    }

    private void union(int loc1, int loc2) {
        int root1 = root(loc1);
        int root2 = root(loc2);
        if (root1 == root2) return;
        if (sz[root1] < sz[root2]) {
            grid[root1] = root2;
            sz[root2] += sz[root1];
        } else {
            grid[root2] = root1;
            sz[root1] += sz[root2];
        }

    }
    private int root(int x) {
        while (grid[x] != x) {
            x = grid[x];
        }
        return x;
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
        return root(grid[0]) == root(grid[grid.length - 1]);

    }
}