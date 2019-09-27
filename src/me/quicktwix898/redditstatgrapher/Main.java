package me.quicktwix898.redditstatgrapher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final String dashes = "------------------------------------------------------------";

        System.out.println(dashes);
	    System.out.println("RedditStat Grapher");
        System.out.println("Please choose an domain you want to analyze today: ");
        System.out.println("a: r/all");
        System.out.println("s: a specific subreddit");
        System.out.println("p: a post");
        System.out.println(dashes);

        char type = reader.readLine().charAt(0);
        while(true) {
            if (type!='a' && type!='s' && type!='p'){
                System.out.println("Please choose an option:");
                type = reader.readLine().charAt(0);
            } else {
                break;
            }
        }

        char analysis='0';
        if(type=='a') {
            System.out.println(dashes);
            System.out.println("Please choose an analysis: ");
            System.out.println("s: subreddits in r/all");
            System.out.println("w: frequently used words");
            System.out.println("p: subreddit popularity over time in r/all");
            System.out.println("a: frequently active times in r/all");
            System.out.println(dashes);
            analysis = reader.readLine().charAt(0);
        } else if(type =='s') {
            System.out.println(dashes);
            System.out.println("Please choose an analysis: ");
            System.out.println("w: frequently used words");
            System.out.println("a: frequently active times");
            System.out.println(dashes);
            analysis = reader.readLine().charAt(0);
        } else if(type=='p') {
            System.out.println("Please choose an analysis: ");
            System.out.println("w: frequently used words");
            System.out.println("u: upvotes over time");
            System.out.println(dashes);
            analysis = reader.readLine().charAt(0);
        }

        while(true) {
            if (analysis!='s' && analysis!='w' && analysis!='u' && analysis!='u' && analysis!='p' && analysis!='a'){
                System.out.println("Please choose an option:");
                analysis = reader.readLine().charAt(0);
            } else break; }

        if(type=='a' && analysis == 's') {
            System.out.println(dashes);
            System.out.println("Please choose an amount of posts: ");
            int posts = Integer.parseInt(reader.readLine());
            //map = getAllSubreddits(posts);
            System.out.println("Please choose a graph type: ");
            System.out.println("b: bar chart");
            System.out.println("p: pie chart");
            char chart = reader.readLine().charAt(0);
            while(true) {
                if(analysis!='b' && analysis!='p') System.out.println("Please choose an option: ");
                else break; }
            System.out.println("Please specify path to save graph (must end in png): ");
            String filename = reader.readLine();
            System.out.println(dashes);
            System.out.println("Generating graph...");
            // if(chart == 'p') graphPie(map, filename);
            // else if(chart == 'b') graphBar(map, filename);
        }
        else if(type == 'a' && analysis == 'w') {
            System.out.println(dashes);
            System.out.println("Please choose an amount of posts: ");
            int posts = Integer.parseInt(reader.readLine());
            // map = getAllParsedWords(posts);
            System.out.println(dashes);
            System.out.println("Please choose a graph type: ");
            System.out.println("w: word cloud");
            System.out.println("b: bar chart");
            System.out.println("p: pie chart");
            char chart = reader.readLine().charAt(0);
            while(true) {
                if(chart!='w' && chart !='b' && chart!='p') System.out.println("Please choose an option: ");
                else break; }
            System.out.println("Please specify path to save graph (must end in png): ");
            System.out.println(dashes);
        }
    }
}
