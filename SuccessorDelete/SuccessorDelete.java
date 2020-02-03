import java.util.Arrays;

public class SuccessorDelete {
    int[] network;
    int[] networkTemp;

    public void initialize(int count) {
        network = new int[count];
        networkTemp = new int[count];

        for (int i = 0; i < count; i++) {
            network[i] = networkTemp[i] = i + 1;
        }
    }

    public void remove(int x) {
        int index = find(x);
        System.out.println(index);
        network[index] = network[x];
        networkTemp[index] = network[x];
        network[x] = -1;
    }

    public int successor(int x) {
        return network[x];
    }

    public int find(int x) {

        int low = networkTemp[0];
        int high = networkTemp[network.length - 1];
        int mid = (low + high) / 2;

        while (low <= high) {
            // mid = (low + high) / 2;
            if (networkTemp[mid] < x)
                low = mid + 1;
            else if (networkTemp[mid] == x)
                return mid;
            else
                high = mid - 1;
            mid = (low + high) / 2;
        }
        return -1;
    }

    public void display() {
        System.out.println(Arrays.toString(network));
    }

    public static void main(String[] args) {
        SuccessorDelete temp = new SuccessorDelete();
        temp.initialize(10);
        // System.out.println(Arrays.toString(temp.network));
        // System.out.println(temp.successor(3)); // 4
        temp.remove(4);
        // System.out.println(temp.successor(3)); // 5
        temp.display();
        temp.remove(5);
        temp.display();
        temp.remove(6);
        System.out.println(temp.successor(3)); // 7
        temp.display();
    }
}