package maze;

import java.util.*;

/**
 * Generates a Maze object using the A* algorithm.
 *
 * @author Tom Philip
 */
public class MazeGenerator {
    static List<List<CostPair>> adj;
    static Map<Integer, Integer> fScore;
    static List<Node> nodes;

    /**
     * Generates a Maze.
     * @return A Maze object, with MazeTiles that point to other MazeTiles, forming a path through the maze.
     */
    public static Maze getMaze(){
        Maze m = Maze.getBlankMaze();
        Random r = new Random();
        List<List<CostPair>> adj = new ArrayList<>();
        for(int y =0; y<25; y++) {
            for (int x = 0; x < 9; x++) {
                adj.add(new ArrayList<>());
            }
        }
        for(int y =0; y<25; y++){
            for(int x = 0; x<9; x++){
                for (int i = 0; i<=1; i++){
                    for(int j = -1; j<=1;j++){
                        if((i|j)==0) continue;
                        if(y+j<0||y+j>=25||x+i>=9) continue;
                        if(i==0&&j==-1) continue;

                        int cost = r.nextInt(100000);
                        adj.get(9*y+x).add(new CostPair(9*(y+j)+x+i, cost));
                        adj.get(9*(y+j)+x+i).add(new CostPair(9*y+x, cost));
                    }
                }
            }
        }
        adj.add(new ArrayList<>());
        for(int x =0; x<9; x++){
            adj.get(x).add(new CostPair(225, 0));
        }
        MazeGenerator.adj = adj;
        nodes = new ArrayList<>();
        for(int i =0; i<=225; i++){
            nodes.add(new Node(i));
        }
        int start = 220;
        int dest = 225;
        LinkedList<Integer> path = aStar(start, dest);
        assert path != null;
        int last=-1;
        for(int i : path){
            if(last==-1){
                last=i;
            } else if(i==225){
                break;
            } else {
                m.maze[last/9][last%9].next = m.maze[i/9][i%9];
                last = i;
            }
        }
        return m;
    }
    private static LinkedList<Integer> reconstructPath(Map<Integer, Integer> cameFrom, int current){
        LinkedList<Integer> path = new LinkedList<>();
        path.add(current);
        while(cameFrom.containsKey(current)){
            current = cameFrom.get(current);
            path.addFirst(current);
        }
        return path;
    }
    private static LinkedList<Integer> aStar(int start, int goal){

        Map<Integer, Integer> cameFrom = new HashMap<>();
        Map<Integer, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);
        Map<Integer, Integer> fScore = new HashMap<>();
        fScore.put(start, h(start));
        MazeGenerator.fScore=fScore;
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        openSet.add(nodes.get(start));
        while(!openSet.isEmpty()){
            int current = openSet.poll().pos;
            if(current==goal){
                return reconstructPath(cameFrom, current);
            }
            for(CostPair neighbour : adj.get(current)){
                int tentativeGScore = gScore.get(current)+neighbour.cost;
                if (tentativeGScore<(gScore.get(neighbour.dest)==null?0x7fffffff:gScore.get(neighbour.dest))){
                    cameFrom.put(neighbour.dest, current);
                    gScore.put(neighbour.dest,tentativeGScore);
                    fScore.put(neighbour.dest,tentativeGScore+h(neighbour.dest));
                    if(!openSet.contains(nodes.get(neighbour.dest))){
                        openSet.add(nodes.get(neighbour.dest));
                    }
                }
            }
        }
        return null;
    }
    private static int h(int n){
        if (n==225) return 0;
        return ((n+1)/9+1)*5;
    }
    private static class CostPair{
        int dest;
        int cost;
        public CostPair(int dest, int cost){
            this.dest=dest;
            this.cost=cost;
        }
    }
    private static class Node implements Comparable<Node>{
        int pos;

        public Node(int pos){
            this.pos=pos;
        }

        @Override
        public int compareTo(Node o) {
            return fScore.get(this.pos)-fScore.get(o.pos);
        }
    }
}
