import java.util.*;

// Define Path class to print the path
public class GraphPath {
    private final List<String> path;

    public GraphPath() {
        path = new ArrayList<>();
    }

    public void addNode(String node) {
        path.add(node);
    }

    public List<String> getPath() {
        return path;
    }

    @Override
    public String toString() {
        reOrderPath(path);
        return String.join(" -> ", path);
    }

    // Without this helper function,
    //  the path printed out prints in reverse
    private static void reOrderPath(List<String> list){
        int left = 0;
        int right = list.size() - 1;

        while(left < right){
            String temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left++;
            right--;
        }
    }

}
