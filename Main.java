import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Driver Code
public class Main {
	static ArrayList<String> allRows = new ArrayList<>();
	static ArrayList<String> row1 = new ArrayList<>();
	static ArrayList<String> row2 = new ArrayList<>();
	static ArrayList<String> row3 = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		Graph g = new Graph();
		Dictionary d = new Dictionary();
		Scanner sc = new Scanner(System.in);
		
		Map<String, List<String>> graph = new HashMap<>();
		
		introMessage();

		do {
			row1.clear();
			System.out.print("Enter Row A (Ex: A, B, C, D): ");

			String[] parts = sc.next().split(",");
        	for (String part : parts) {
				if(allRows.contains(part.toUpperCase()))
				{
					int occurrences = countStringsStartingWith(allRows,part.toUpperCase());
					String formattedString = (part.trim() + occurrences).toUpperCase();
					row1.add(formattedString);
					allRows.add(formattedString);
				}
				else
				{
					String formattedString = (part.trim().toUpperCase());
					row1.add(formattedString);
					allRows.add(formattedString);
				}
            	
        	}
		} while ((row1.size() > 4 || row1.size() < 4));

		do {
			row2.clear();
			System.out.print("Enter Row B (Ex: A, B, C, D, E): ");

			String[] parts = sc.next().split(",");
        	for (String part : parts) {
            	if(allRows.contains(part.toUpperCase()))
				{
					int occurrences = countStringsStartingWith(allRows,part.toUpperCase());
					String formattedString = (part.trim() + occurrences).toUpperCase();

					row2.add(formattedString);
					allRows.add(formattedString);
				}
				else
				{
					String formattedString = (part.trim().toUpperCase());
					row2.add(formattedString);
					allRows.add(formattedString);
				}
        	}
		} while ((row2.size() > 5 || row2.size() < 5));
		
		do {
			row3.clear();
			System.out.print("Enter Row C (Ex: A, B, C, D): ");

			String[] parts = sc.next().split(",");
        	for (String part : parts) {
            	if(allRows.contains(part.toUpperCase()))
				{
					int occurrences = countStringsStartingWith(allRows,part.toUpperCase());
					String formattedString = (part.trim() + occurrences).toUpperCase();

					row3.add(formattedString);
					allRows.add(formattedString);
				}
				else
				{
					String formattedString = (part.trim().toUpperCase());
					row3.add(formattedString);
					allRows.add(formattedString);
				}
        	}
		} while ((row3.size() > 4 || row3.size() < 4));

		System.out.println("\nFollow same letter as below:");

		graph.put(row1.get(0), nodeListFormatter(new int[][] {{0,1},{1,1},{1,0}}));
		graph.put(row1.get(1), nodeListFormatter(new int[][] {{0,0},{1,1},{1,2},{0,2}}));
		graph.put(row1.get(2), nodeListFormatter(new int[][] {{0,1},{1,2},{1,3},{0,3}})); 
		graph.put(row1.get(3), nodeListFormatter(new int[][] {{0,2},{1,3},{1,4}}));
		graph.put(row2.get(0), nodeListFormatter(new int[][] {{0,0},{1,1},{2,0}}));
		graph.put(row2.get(1), nodeListFormatter(new int[][] {{0,0},{1,0},{2,0},{2,1},{1,2},{0,1}}));
		graph.put(row2.get(2), nodeListFormatter(new int[][] {{0,1},{1,1},{2,1},{2,2},{1,3},{0,2}}));
		graph.put(row2.get(3), nodeListFormatter(new int[][] {{0,2},{1,2},{2,2},{2,3},{1,4},{0,3}}));
		graph.put(row2.get(4), nodeListFormatter(new int[][] {{0,3},{1,3},{2,3}}));
		graph.put(row3.get(0), nodeListFormatter(new int[][] {{1,0},{1,1},{2,1}}));
		graph.put(row3.get(1), nodeListFormatter(new int[][] {{2,0},{1,1},{1,2},{2,2}}));
		graph.put(row3.get(2), nodeListFormatter(new int[][] {{2,1},{1,2},{1,3},{2,3}}));
		graph.put(row3.get(3), nodeListFormatter(new int[][] {{2,2},{1,3},{1,4}}));

		for (int i = 0; i < 5; i++) {
			System.out.println(" \n" + row1);
			System.out.println(row2);
			System.out.println(" " + row3);
			System.out.print("\nEnter Starting Letter: ");
			String startNode = (sc.next()).toUpperCase();
			System.out.print("Enter Word Length: ");
			int pathLength =  sc.nextInt();

			List<List<String>> result = g.findPaths(graph, startNode, pathLength);

			System.out.println("\nPossible Word Options: ");
			for (List<String> path : result) {
				
				if(d.contains(concatenation(path)))
				{
					System.out.println(concatenation(path));
				}
			}
		}
		
	}
	

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

	public static String introMessage()
	{
		String result = "";
		result += ("Row A:  _ _ _ _ \n");
		result += ("Row B: _ _ _ _ _\n");
		result += ("Row C:  _ _ _ _ \n");
		return result;
	}

	public static List<String> nodeListFormatter(int[][] indexes)
	{
		//[[Graph Row , Graph Col], [Graph Row , Graph Col]]
		ArrayList<String> stringArr = new ArrayList<>();

		for (int[] arr : indexes) {
			switch (arr[0]) 
			{
				case 0:
					stringArr.add(row1.get(arr[1]));
					break;
				case 1:
					stringArr.add(row2.get(arr[1]));
					break;
				case 2:
					stringArr.add(row3.get(arr[1]));
					break;
			}
		}

		return stringArr;
	}

	public static int countStringsStartingWith(List<String> list, String startingChar) {
        return (int) list.stream().filter(s -> s.startsWith((startingChar))).count();
    }
}



