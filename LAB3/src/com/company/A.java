package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Created by sbelan on 3/29/2017.
 */
public class A {


    private int n,m,cycleStart;
    private int colors[],numbers[];
    private ArrayList<Integer> graph[];
    private BufferedReader cin;
    private BufferedWriter cout,console;
    private Stack<Integer> stack;

    public void readInfo() throws IOException {
        cout = new BufferedWriter(new FileWriter("cycle.out"));
        cin = new BufferedReader(new FileReader("cycle.in"));
        console =  new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = cin.readLine().split(" ");
        n = Integer.parseInt( s[0] );
        m = Integer.parseInt( s[1] );

        colors = new int[n];
        numbers = new int[n];
        stack = new Stack();
        Arrays.fill(colors, 0);
        graph = new ArrayList[n];

        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i <= m; ++i) {
            String str[] = cin.readLine().split(" ");
            //console.write( str[0]+" "+str[1]+"\n" );
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            graph[x-1].add(y-1);
        }
        //printGraph();
        for (int i = 0; i < n ; i++) {
            if(dfs(i)){
                printCycle();
                return;
            }
        }
        cout.write("NO");
        cout.close();
        cin.close();
        console.close();

    }
    void main(){
        A sample = new A();
        sample.readInfo();
    }
}
