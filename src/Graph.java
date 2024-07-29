package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph {
    
    public List<List<String>> findPaths(Map<String, List<String>> graph, String start, int n) {
        List<List<String>> paths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(start);
        dfs(graph, start, n, path, paths);
        return paths;
    }

	//Depth First Search Implementation
    private void dfs(Map<String, List<String>> graph, String node, int n, List<String> path, List<List<String>> paths) {
        if (path.size() == n) {
            paths.add(new ArrayList<>(path));
            return;
        }

        if (!graph.containsKey(node)) {
            return;
        }

        for (String neighbor : graph.get(node)) {
            path.add(neighbor);
            dfs(graph, neighbor, n, path, paths);
            path.remove(path.size() - 1);
        }
    }
    
}
