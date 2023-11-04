import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Driver Code
public class Main {

	public static String numRemover(String input)
	{
		return input.replaceAll("\\d","");
	}

	public static String concatenation(List<String> strList)
	{
		String result = "";

		for (String arrElement : strList) {
			result += numRemover(arrElement);
		}
		return result.toLowerCase();
	}
	public static List<List<String>> findPaths(Map<String, List<String>> graph, String start, int n) {
        List<List<String>> paths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        path.add(start);
        dfs(graph, start, n, path, paths);
        return paths;
    }

	//Depth First Search Implementation
    private static void dfs(Map<String, List<String>> graph, String node, int n, List<String> path, List<List<String>> paths) {
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
	
	public static void main(String[] args) throws IOException {
		Map<String, List<String>> graph = new HashMap<>();
		graph.put("A", (Arrays.asList("B", "C")));
		graph.put("B", (Arrays.asList("A", "C", "D")));
		graph.put("C", (Arrays.asList("A", "B", "D")));
		graph.put("D", (Arrays.asList("B", "C")));

		graph.put("A1", (Arrays.asList("C","T","L")));
		graph.put("L", (Arrays.asList("A1","T","I","A2")));
		graph.put("A2", (Arrays.asList("L","I","U","N1")));
		graph.put("N1", (Arrays.asList("A2","U","A3")));
		graph.put("C", (Arrays.asList("A1","T","A4")));
		graph.put("T", (Arrays.asList("A1","C","A4","N2","I","L")));
		graph.put("I", (Arrays.asList("T","N2","G","U","A2","L")));
		graph.put("U", (Arrays.asList("A2","I","G","M","A3","N1")));
		graph.put("A3", (Arrays.asList("N1","U","M")));
		graph.put("A4", (Arrays.asList("C","T","N2")));
		graph.put("N2", (Arrays.asList("A4","T","I","G")));
		graph.put("G", (Arrays.asList("N2","I","U","M")));
		graph.put("M", (Arrays.asList("G","U","A3")));

		String startNode = "G";
		int pathLength = 5;

		List<List<String>> result = findPaths(graph, startNode, pathLength);

		Dictionary d = new Dictionary();
		for (List<String> path : result) {
			
			if(d.contains(concatenation(path)))
			{
				System.out.println(concatenation(path));
			}
		}

		
	}
}



