import java.util.ArrayList;
import java.util.Arrays;

public class WithArray {
    ArrayList<Integer> network;

    public void initialize(int count) {
        network = new ArrayList<Integer>();

        for (int i = 1; i < count; i++) {
            network.add(i);
        }
    }

    public int find(int x) {

        int low = network.get(0);
        int high = network.get(network.size() - 1);
        int mid = (low + high) / 2;

        while (low <= high) {
            if (network.get(mid) < x)
                low = mid + 1;
            else if (network.get(mid) == x)
                return mid;
            else
                high = mid - 1;
            mid = (low + high) / 2;
        }
        return -1;
    }

    public void successor(int x) {
        System.out.println(network.get(find(x) + 1));
    }

    public void remove(int x) {
        network.remove(find(x));
    }

    public void display() {
        System.out.println(Arrays.toString(network.toArray()));
    }

    public static void main(String[] args) {
        WithArray demo = new WithArray();
        demo.initialize(10);
        demo.display();
        demo.successor(5);
        demo.remove(6);
        demo.remove(7);
        demo.display();
        demo.successor(5);
        demo.remove(4);
        demo.successor(3);
    }
}