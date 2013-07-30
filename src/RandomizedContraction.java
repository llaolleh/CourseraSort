import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.Random;


public class RandomizedContraction {


	
	public static void main(String[] args) throws FileNotFoundException
	{
		//TreeMap<Integer, ArrayList<Integer>> graph  = readFile("kargerMinCut.txt");
		//final HashMap<Integer, ArrayList<Integer>> graph  = readFile("test1.txt");
		
		
		//graph.keySet();
		//System.out.println(graph);
		//graph.remove(1);
		//graph.remove(2);
		//graph.remove(3);
		//graph.remove(4);
		int trial1 = (int)Math.pow(200,2);
		int trial2 = 100;
		
		long startTime = System.currentTimeMillis();
		
		runEdgeContract();
		long endTime = System.currentTimeMillis();
		System.out.println(String.valueOf(endTime-startTime));
	
	}
	
	//
	
	
	public static void runEdgeContract() throws FileNotFoundException
	{
		int minLength = 200-1;
		int changeTemp = 0;
	for(int x=0; x< 10000; x++)
	{
		//long startTime = System.currentTimeMillis();
		HashMap<Integer, ArrayList<Integer>> g  = readFile("kargerMinCut.txt");
		//long endTime = System.currentTimeMillis();
		//System.out.println("reading time: " + String.valueOf(endTime-startTime));
		
		//startTime =System.currentTimeMillis();
		int tempLength = edgeContract(g);
		//endTime =System.currentTimeMillis();
		//System.out.println("compute time: " + String.valueOf(endTime-startTime));
		if(tempLength < minLength)
		{
			changeTemp++;
			minLength = tempLength;
		}
	}
	System.out.println("minlength: "+minLength);
	System.out.println(changeTemp);
}
	
	public static int edgeContract(HashMap<Integer, ArrayList<Integer>> graph)
	{
		Set<Integer> verticesLeft = graph.keySet();
		//System.out.println(graph.keySet());
		while(verticesLeft.size() > 2)
		{
			// we need to pick an adge at random
			int[] randVertices = pickRandomEdge(graph);
			
			/*
			for(Integer i: randVertices)
			{
				System.out.print(" " +i);
			}
			System.out.println();
			*/
			graph = mergeVertices(randVertices, graph);
		}
		
		for(Integer obj: graph.keySet() )
		{
			return graph.get(obj).size();
		}
		
			/*
		for(Integer obj: graph.keySet() )
		{
			System.out.println("key: "+ obj + " length: "+graph.get(obj).size()+ " values: "+ graph.get(obj) );
		}
		
		*/
		return 200;
	}
	
	
	//public static renameVertex
	
	
	
	public static HashMap<Integer, ArrayList<Integer>> mergeVertices(int[] vertices, HashMap<Integer, ArrayList<Integer>> graph)
	{
		ArrayList<Integer> commonVertices = graph.get(vertices[0]);
		/*
		System.out.println("keyset: "+graph.keySet());
		
		System.out.println("vertices: "+ vertices[0]+ ", " + vertices[1]);
		*/
		commonVertices.addAll(graph.get(vertices[1]));
		//System.out.println("common vertices before: "+commonVertices);
		Iterator<Integer> iter = commonVertices.iterator();
		//removes self-referencing loops
		while(iter.hasNext())
		{
			int obj = iter.next();
			if( obj == vertices[0] || obj == vertices[1] )
			{
				iter.remove();
			}
			ArrayList<Integer> arList = graph.get(obj);
			//System.out.println("key: "+ key + " " + arList);
			if(graph.get(obj).contains(vertices[1])) {
				//then add the name of the new joined label
				arList.remove((Object)vertices[1]);
				graph.get(obj).add(vertices[0]);
			}
		}
		//System.out.println("common vertices: "+ commonVertices);
		
		
		//remove references to the other vertex label( we throw out 2 from [1,2], label the new vertex as 1
		
		
		/* YOU IDIOT YOU HAVE TO TAKE OUT THE REFERENCES TO THE CHOSEN RANDOM VERTICES FROM THE KEYSET
		 OF THE MAP. HOW COULD YOU FORGET THAT? ...SMH. Specifically, the label of one of the vertices you're throwing out
		 exm: you join vertices 1 and 2, choose to label the joined verties as "1"
		 Now you have to take out all the references to 2, and replace it with one
		
		
		
		*/
		graph.remove(vertices[1]);
		
		//rename the merged vertex as the first element of the array
		graph.put(vertices[0], commonVertices);
		
		return graph;
		
	}
	//picks two vertices, or an "edge" to contract
	public static int[] pickRandomEdge(HashMap<Integer, ArrayList<Integer>> graph)
	{
		//in case the graph is empty - probably won't happen due to while loop, but just in case
		if(graph.keySet().size() == 0)
			return null;
		
		Set<Integer> verticesLeft = graph.keySet();
		int randVertIndex = new Random().nextInt(verticesLeft.size());
		int vertex1 = (int)verticesLeft.toArray()[0];
		int index = 0; // counting mechanism to get the ith item in the set since java TreeSet doesn't implement .get Smh -.-
		for(Integer i: verticesLeft)
		{
			if( index == randVertIndex )
				vertex1 = i;
			index++;
		}
		// the size of the arrayList that contains all the vertices that vertex1 has 
		int arrayListSize = graph.get(vertex1).size();
		int arrayListVertex = graph.get(vertex1).get(new Random().nextInt(arrayListSize));
		
		//returns the edge, or vertices in the 2 element array.
		int[] vertices = new int[2];
		vertices[0] = vertex1;
		vertices[1] = arrayListVertex;
		return vertices;
		
	}
	
	
	//converts TextFile into a TreeMap representation of the graph
	public static HashMap<Integer, ArrayList<Integer>> readFile(String filename) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File(filename));
		HashMap<Integer, ArrayList<Integer>> graph  = new HashMap<Integer, ArrayList<Integer>>();
		while(scanner.hasNextLine())
		{
			ArrayList<Integer> intArrList= new ArrayList<Integer>();
			String line = scanner.nextLine();
			Scanner lineReader = new Scanner(line);
		    String[] s = line.split(" ");
		    int key = lineReader.nextInt();
		    while(lineReader.hasNextInt())
		    {
		    	intArrList.add(lineReader.nextInt());
			}
		    graph.put(key, intArrList);
		}
		scanner.close();
		return graph;
	}
	
	
	
	

}

