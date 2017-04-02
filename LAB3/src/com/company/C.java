package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by sergey on 02.04.2017.
 */


public class C {
    private int n,m;
    private double graph[][],points[][],key[],p[], infinity = Double.MAX_VALUE;
    private BufferedReader cin;
    private BufferedWriter cout,console;
    Queue<ElemOfQueue> Q;

    private double weight(double x1,double y1,double x2,double y2){
        return Math.sqrt( Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2)  );
    }

    private void pushToQueue() throws IOException{
        for (int i = 0; i < n ; i++) {
            ElemOfQueue element = new ElemOfQueue(i,key[i]);
            ElemOfQueue element2 = new ElemOfQueue(i,key[i]);
            Q.add(element);
            if(element.equals(element2))
                console.write("lol ");
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

    private void primFindMst()throws IOException {
        int r = 0;
        key[r] = 0;
        pushToQueue();
        while(!Q.isEmpty()){
            ElemOfQueue v = Q.poll();
            for (int u = 0; u < graph[v.vertex].length ; u++) {
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
        cout = new BufferedWriter(new FileWriter("spantree2.out"));
        cin = new BufferedReader(new FileReader("spantree2.in"));
        console =  new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = cin.readLine().split(" ");
        n = Integer.parseInt( s[0] );
        m = Integer.parseInt(s[1]);
        Q = new PriorityQueue<ElemOfQueue>(n,idComparator);
        graph = new double[n][n];
        p = new double[n];
        key = new double[n];
        points = new double[n][2];
        Arrays.fill(key, infinity);

        for (int i = 1; i <= n; ++i) {
            String str[] = cin.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            int w = Integer.parseInt(str[2]);
            graph[x-1][y-1] = w;
            graph[y-1][x-1] = w;
        }
        //printMatrix();
        primFindMst();
        printSizeMST();
        cout.close();
        cin.close();
        console.close();
    }
    public static void main(String[] args) throws IOException{
        C sample = new C();
        sample.readInfo();
    }
}
