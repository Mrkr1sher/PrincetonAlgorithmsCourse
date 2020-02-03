public class UnionFindGreatest {
    int[] highest;
    int[] network;
    int[] size;

    public void initialize(int count) {
        highest = new int[count];
        size = new int[count];
        network = new int[count];
        for (int i = 0; i < count; i++) {
            highest[i] = network[i] = i;
            size[i] = 1;
        }
    }

    public void union(int a, int b) {
        int aRoot = root(a);
        int bRoot = root(b);
        if (aRoot == bRoot)
            return;
        if (size[aRoot] > size[bRoot]) {
            network[bRoot] = aRoot;
            size[aRoot] += size[bRoot];
        } else {
            network[aRoot] = bRoot;
            size[bRoot] += size[aRoot];
        }
        if (highest[aRoot] > highest[bRoot]) {
            highest[bRoot] = highest[aRoot];
        }
    }

    public int root(int a) {
        while (a != network[a])
            a = network[a];
        return a;
    }

    public int find(int i) {
        int root = root(i);
        return highest[root];

    }

    public static void main(String[] args) {
        UnionFindGreatest test = new UnionFindGreatest();

        test.initialize(10);

        test.union(3, 7);
        test.union(1, 2);
        test.union(5, 2);
        test.union(9, 3);
        test.union(4, 8);
        test.union(9, 6);
        System.out.println(test.find(2));
        System.out.println(test.find(4));
        System.out.println(test.find(7));

    }
}