public class SocialNetworkingConnectivity {
    int[] network;
    int[] size;
    int groups;

    public void initialize(int count) {
        network = new int[count];
        size = new int[count];
        groups = count;
        for (int i = 0; i < count; i++)
            network[i] = size[i] = i;
    }

    public void union(int a, int b) {
        int aRoot = root(a);
        int bRoot = root(b);
        if (aRoot == bRoot)
            return;
        groups--;
        if (size[aRoot] > size[bRoot]) {
            network[bRoot] = aRoot;
            size[aRoot] += size[bRoot];
        } else {
            network[aRoot] = bRoot;
            size[bRoot] += size[aRoot];
        }
    }

    public int root(int a) {
        while (a != network[a]) {
            a = network[a];
        }
        return a;
    }

    public static void main(String[] args) {

        SocialNetworkingConnectivity test = new SocialNetworkingConnectivity();

        TimeStamp[] timeStamps = { new TimeStamp(3, 7, 3), new TimeStamp(1, 2, 4), new TimeStamp(9, 5, 5),
                new TimeStamp(5, 7, 6), new TimeStamp(0, 8, 7), new TimeStamp(0, 4, 8), new TimeStamp(3, 7, 9),
                new TimeStamp(2, 6, 10), new TimeStamp(7, 9, 11), new TimeStamp(2, 5, 12), new TimeStamp(6, 3, 13),
                new TimeStamp(0, 1, 14), new TimeStamp(4, 9, 15), new TimeStamp(2, 7, 16), new TimeStamp(5, 6, 17),
                new TimeStamp(1, 8, 18) };

        test.initialize(10);

        for (int i = 0; i < timeStamps.length; i++) {
            test.union(timeStamps[i].a, timeStamps[i].b);
            if (test.groups == 1) {
                System.out.println(timeStamps[i].time);
                break;
            }
        }
    }
}

class TimeStamp {
    int a;
    int b;
    int time;

    public TimeStamp(int a, int b, int time) {
        this.a = a;
        this.b = b;
        this.time = time;
    }
}
