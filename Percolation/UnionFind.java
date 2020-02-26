package Percolation;

public class UnionFind {

    int[] arr;
    int[] size;
    int numSets;

    public UnionFind(int n) {
        numSets = n;
        arr = new int[n];
        size = new int[n];
        for(int i = 0; i<arr.length; i++){
            arr[i] = i;
            size[i] = 1;
        }
    }

    private void validate(int vertex) {
        if(vertex <0 || vertex>arr.length-1){
            throw new IllegalArgumentException(vertex + " is not a valid index");
        }
    }

    public int sizeOf(int v1) {
        validate(v1);
        return size[find(v1)];
    }

    public int parent(int v1) {
        validate(v1);
        return arr[v1];
    }

    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return (find(v1) == find(v2));

    }

    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int v1Root = find(v1);
        int v2Root = find(v2);
        if(v1Root == v2Root)return;
        if(size[v1Root]>size[v2Root]){
            arr[v2Root] = v1Root;
            size[v1Root] += size[v2Root];
        }else{
            arr[v1Root] = v2Root;
            size[v2Root] += size[v1Root];
        }
        numSets--;
    }

    public int find(int vertex) {
        validate(vertex);
        while(parent(vertex) != vertex){
            vertex = parent(vertex);
        }
        return vertex;
    }

}
