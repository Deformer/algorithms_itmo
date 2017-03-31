package com.company;

import java.io.*;
import java.util.*;


/**
 * Created by sbelan on 3/31/2017.
 */
class ElemOfQueue{
    public int vertex;
    public double weight;
    ElemOfQueue(int vertex,double weight){
        this.vertex = vertex;
        this.weight = weight;
    }
};

public class B {
    private int n;
    private double graph[][],points[][],key[],p[], infinity = Double.MAX_VALUE;
    private BufferedReader cin;
    private BufferedWriter cout,console;
    Queue<ElemOfQueue> Q;

    private double weight(double x1,double y1,double x2,double y2){
        return Math.sqrt( Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2)  );
    }

    private void pushToQueue(){
        for (int i = 0; i < n ; i++) {
            ElemOfQueue element = new ElemOfQueue(i,key[i]);
            Q.add(element);
        }
    }

    public static Comparator<ElemOfQueue> idComparator = new Comparator<ElemOfQueue>(){

        @Override
        public int compare(ElemOfQueue v1, ElemOfQueue v2) {
            if(v1.weight > v2.weight)
                return 1;
            else if(v1.weight < v2.weight)
                return -1;
            else
                return 0;
        }
    };

    private void primFindMst()throws IOException{
        int r = 0;
        key[r] = 0;
        pushToQueue();
        while(!Q.isEmpty()){
            ElemOfQueue v = Q.poll();
            for (int u = 0; u < graph[v.vertex].length ; u++) {
                if(u != v.vertex) {
                    double u_weight = graph[v.vertex][u];
                    ElemOfQueue u_element = new ElemOfQueue(u, key[u]);
                    if (key[u] > u_weight) {
                        p[u] = v.vertex;
                        key[u] = u_weight;
                        Q.remove(u_element);
                        Q.add(new ElemOfQueue(u, u_weight));
                    }
                }
            }
        }
    }

    private void printMatrix() throws IOException{
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++) {
                cout.write(String.valueOf(graph[i][j]));
                cout.write(" ");
            }
            cout.newLine();
        }
    }

    private void printSizeMST() throws IOException{
        double res = 0;
        for (int i = 0; i < n ; i++) {
            res += key[i];
        }
        cout.write(String.valueOf(res));
    }

    public void readInfo() throws IOException {
        cout = new BufferedWriter(new FileWriter("spantree.out"));
        cin = new BufferedReader(new FileReader("spantree.in"));
        console =  new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = cin.readLine().split(" ");
        n = Integer.parseInt( s[0] );
        Q = new PriorityQueue<ElemOfQueue>(n,idComparator);
        graph = new double[n][n];
        p = new double[n];
        key = new double[n];
        points = new double[n][2];
        Arrays.fill(key, infinity);

        for (int i = 1; i <= n; ++i) {
            String str[] = cin.readLine().split(" ");
            double x = Double.parseDouble(str[0]) + 10000;
            double y = Double.parseDouble(str[1]) + 10000;
            points[i-1][0] = x;
            points[i-1][1] = y;
            for (int j = 0; j < i ; j++) {
                double weightOfEdge = weight(points[i-1][0], points[i-1][1],points[j][0], points[j][1]);
                graph[j][i-1] = weightOfEdge;
                graph[i-1][j] = weightOfEdge;
            }
        }
        primFindMst();
        printSizeMST();
        cout.close();
        cin.close();
        console.close();
    }
    public static void main(String[] args) throws IOException{
        B sample = new B();
        sample.readInfo();
    }
}
