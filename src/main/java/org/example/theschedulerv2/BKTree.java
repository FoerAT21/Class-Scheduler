package org.example.theschedulerv2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BKTree {

    private class Node{
        private String word;
        private HashMap<Integer, Node> childrenAtDistances;

        public Node(String word){
            this.word = word;
            childrenAtDistances = new HashMap<>();
        }

        public Node(){
            this.word = "";
            childrenAtDistances = new HashMap<>();
        }

        public Node childAtDistance(int dist){
            return childrenAtDistances.get(dist);
        }

        public void addChild(int dist, String word){
            Node add = new Node(word);
            childrenAtDistances.put(dist, add);
        }

        public HashMap<Integer, Node> getAllChildren(){
            return childrenAtDistances;
        }
    }

    private final int THRESHOLD = 3;
    private Node ROOT;


    public BKTree(String filePath) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filePath));
        ROOT = new Node(input.next());

        while(input.hasNext()){
            add(ROOT, input.next());
        }
    }

    private void add(Node root, String word){
        int distance = levDistance(word, root.word);

        if(root.childAtDistance(distance) != null)
            add(root.childAtDistance(distance), word);
        else
            root.addChild(distance, word);
    }

    public ArrayList<String> search(String word){
        ArrayList<String> searchResults = new ArrayList<>();
        if(levDistance(ROOT.word, word) <= THRESHOLD) searchResults.add(word);

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(ROOT);

        while(!nodes.isEmpty()){
            Node temp = nodes.remove();

            for(int x : temp.childrenAtDistances.keySet()){
                Node child = temp.childrenAtDistances.get(x);
                if(x <= THRESHOLD)
                    searchResults.add(child.word);
                nodes.add(child);
            }
        }

        return searchResults;
    }

    private int min(int left, int top, int diagonal){
        return Math.min(diagonal, Math.min(left, top));
    }

    private int levDistance(String x, String y){
        if(x.equals("")) return y.length();
        if(y.equals("")) return x.length();
        if(x.equals(y)) return 0;

        int[][] levVals = new int[2][x.length()+1];

        for(int i = 0; i <= x.length(); i++){
            levVals[0][i] = i;
        }

        for(int indexY= 0; indexY < y.length(); indexY++){
            // Set the empty string distance
            levVals[1][0] = levVals[0][0] + 1;

            char yChar = y.charAt(indexY);
            for(int indexX = 1; indexX <= x.length(); indexX++){
                if(yChar == x.charAt(indexX-1)) {
                    levVals[1][indexX] = levVals[0][indexX-1];
                }else{
                    levVals[1][indexX] = min(
                            levVals[1][indexX-1]+1,
                            levVals[0][indexX]+1,
                            levVals[0][indexX-1]+1
                    );
                }
            }

            for(int i = 0; i <= x.length(); i++){
                levVals[0][i] = levVals[1][i];
            }

        }

        return levVals[1][x.length()];
    }

    public static void main(String[] args) throws FileNotFoundException {
        BKTree test = new BKTree("C:\\Users\\FOERSTAT21\\OneDrive - Grove City College\\Semester 6\\Software Engineering\\TheSchedulerV2\\src\\course_id_dict.txt");
        System.out.println(test.search("COMP300"));
    }

}
