package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EqualStacks {
    public static void main(String[] args) {
        List<Integer> h1 = new ArrayList<>();
        List<Integer> h2 = new ArrayList<>();
        List<Integer> h3 = new ArrayList<>();


        h1.add(5);
        h1.add(2);
        h1.add(3);

        h2.add(4);
        h2.add(3);
        h2.add(2);

        h3.add(1);
        h3.add(1);
        h3.add(4);
        h3.add(1);

        System.out.println(equalStacks(h1, h2, h3));

    }

    //Draft code
    //Solution code 거의 동일
    //Time Complexity: O(n1+n2+n3)
    //Space Complexity: O(n1+n2+n3) for the 3 new stacks that we create.
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        int[] height = new int[3];
        Stack<Integer> s1 = new Stack();
        Stack<Integer> s2 = new Stack();
        Stack<Integer> s3 = new Stack();

        for (int i = h1.size() - 1; i >= 0; i--) {
            height[0] += h1.get(i);
            s1.push(h1.get(i));
        }
        for (int i = h2.size() - 1; i >= 0; i--) {
            height[1] += h2.get(i);
            s2.push(h2.get(i));
        }
        for (int i = h3.size() - 1; i >= 0; i--) {
            height[2] += h3.get(i);
            s3.push(h3.get(i));
        }

        while (!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty()) {
            int max = Math.max(Math.max(height[0], height[1]), height[2]);

            if (height[0] == height[1] && height[1] == height[2]) {
                return height[0];
            }

            if (height[0] == max) {
                height[0] -= s1.pop();
            }
            if (height[1] == max) {
                height[1] -= s2.pop();
            }
            if (height[2] == max) {
                height[2] -= s3.pop();
            }
        }
        return 0;
    }
}
