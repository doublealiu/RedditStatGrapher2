package me.quicktwix898.redditstatgrapher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String dashes = "------------------------------------------------------------";

        System.out.println(dashes);
	    System.out.println("RedditStat Grapher");
        System.out.println("Please choose an domain you want to analyze today: ");
        System.out.println("a: r/all");
        System.out.println("s: a specific subreddit");
        System.out.println("p: a list of posts");
        System.out.println(dashes);

        char type = reader.readLine().charAt(0);
        while(true) {
            if (char != ) {

            }
        }

        System.out.println(dashes);
        System.out.println("Please choose an analysis: ");
        if(type=='a') System.out.println("s: Subreddits in r/all");
        System.out.println("w: Frequently used words");
        if(type=='p') System.out.println("u: Upvotes over time");
        if(type!='p') System.out.println("p: Popularity over time");
        System.out.println("a: Freqency of activity");
        System.out.println(dashes);

        char analysis = reader.readLine().charAt(0);
    }
}
