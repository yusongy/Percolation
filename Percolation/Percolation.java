package Percolation;

//import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    int N;
    int[][] arr;
    int[][] open;
    int[][] full;
    UnionFind QU;
    public Percolation(int N){
        this.N = N;
        if(N<0){
            throw new IllegalArgumentException("N is smaller than 0");
        }
        arr = new int[N][N];
        for(int row = 0; row<N; row++){
            for(int col = 0; col<N; col++){
                arr[row][col] = col+row;
            }
        }
        open = new int[N][N];
        for(int row = 0; row<N; row++){
            for(int col = 0; col<N; col++){
                open[row][col] = col+row;
            }
        }
        full = new int[N][N];
        for(int row = 0; row<N; row++){
            for(int col = 0; col<N; col++){
                full[row][col] = col+row;
            }
        }
        QU = new UnionFind(N*N);

    }
    public void open(int row, int col){
        open[row][col]=1;
        if(row==0){
            full[row][col] = 1;
        }
        //checkup
        if(row>0){
            if(isOpen(row-1, col)){
                QU.union((row-1)*N+col, row*N+col);
                if(full[row-1][col]==1||full[row][col]==1){
                    changeFull(row*N+col);
                }
            }
        }
        //checkdown
        if(row<(N-1)){
            if(isOpen(row+1, col)){
                QU.union((row+1)*N+col, row*N+col);
                if(full[row+1][col]==1||full[row][col]==1){
                    changeFull(row*N+col);
                }
            }
        }
        //checkleft
        if(col>0){
            if(isOpen(row, col-1)){
                QU.union(row*N+col-1, row*N+col);
                if(full[row][col-1]==1||full[row][col]==1){
                    changeFull(row*N+col);
                }
            }
        }
        //checkright
        if(col<(N-1)){
            if(isOpen(row, col+1)){
                QU.union(row*N+col+1, row*N+col);
                if(full[row][col+1]==1||full[row][col]==1){
                    changeFull(row*N+col);
                }
            }
        }
    }
    public boolean isOpen(int row, int col)           {
        return (open[row][col]==1);
    }
    public boolean isFull(int row, int col)  {
        int index = QU.find(row*N+col);
        int Rcol = index%N;
        int Rrow = (index-Rcol)/N;
        return (full[Rrow][Rcol]==1);
    }
    public int numberOfOpenSites() {
        int num =0;
        for(int i = 0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if (full[i][j] == 1) {
                    num +=1;
                }
            }
        }
        return num;
    }

    public boolean percolates() {
        for(int i = 0; i<N;i++){
            for(int j = (N-1)*N; j<N*N; j++) {
                if(QU.connected(i, j)){
                    return true;
                }
            }
        }
        return false;
    }

    public void changeFull(int i){
        int index = QU.find(i);
        int Rcol = index%N;
        int Rrow = (index-Rcol)/N;
        full[Rrow][Rcol] = 1;
    }

    public static void main(String[] args)  {
        PercolationFactory factory = new PercolationFactory();
        PercolationStats Stats = new PercolationStats(10, 30, factory);
        System.out.println("Stats.mean();"+Stats.mean());
        System.out.println("Stats.stddev();"+Stats.stddev());
        System.out.println("Stats.confidenceLow;"+Stats.confidenceLow());
        System.out.println("Stats.confidenceHigh();"+Stats.confidenceHigh());
    }
    
}
