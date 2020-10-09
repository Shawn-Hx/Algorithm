package cn.edu.nju.design;

import java.util.*;

/**
 * LeetCode 355. Design Twitter
 */
public class Twitter {

    private int time;

    private static class Tweet {
        int tweetId, time;
        Tweet next;
        Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    private final Map<Integer,Tweet> userTweets;
    private final Map<Integer, Set<Integer>> userFollows;

    /** Initialize your data structure here. */
    public Twitter() {
        userTweets = new HashMap<>();
        userFollows = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet head = userTweets.getOrDefault(userId, new Tweet(-1, -1));
        Tweet newTweet = new Tweet(tweetId, time++);
        newTweet.next = head.next;
        head.next = newTweet;
        userTweets.putIfAbsent(userId, head);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        Queue<Tweet> queue = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.time, t1.time));
        Set<Integer> follows = userFollows.getOrDefault(userId, new HashSet<>());
        follows.add(userId);
        for (int followee : follows)
            if (userTweets.containsKey(followee) && userTweets.get(followee).next != null)
                queue.add(userTweets.get(followee).next);
        while (!queue.isEmpty() && res.size() < 10) {
            Tweet tweet = queue.remove();
            res.add(tweet.tweetId);
            if (tweet.next != null)
                queue.add(tweet.next);
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> follows = userFollows.getOrDefault(followerId, new HashSet<>());
        follows.add(followeeId);
        userFollows.putIfAbsent(followerId, follows);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> follows = userFollows.getOrDefault(followerId, new HashSet<>());
        follows.remove(followeeId);
        userFollows.putIfAbsent(followerId, follows);
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        twitter.getNewsFeed(1).forEach(e -> System.out.print(e + " "));
        System.out.println();
        twitter.unfollow(1, 2);
        twitter.getNewsFeed(1).forEach(e -> System.out.print(e + " "));
    }
}
