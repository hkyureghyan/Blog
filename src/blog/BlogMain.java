package blog;

import blog.storage.PostStorageImpl;
import blog.model.Post;

import java.util.Date;
import java.util.Scanner;

public class BlogMain {
    private static final int EXIT = 0;
    private static final int ADD_POST = 2;
    private static final int SEARCH_POST = 3;
    private static final int POSTS_BY_CATEGORY = 4;
    private static final int ALL_POSTS = 5;
    private static Scanner scanner = new Scanner(System.in);
    private static PostStorageImpl postStorage = new PostStorageImpl();


    public static void main(String[] args) {
        boolean isRun = true;
        int command;

        while (isRun) {
            printCommands();
            command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case SEARCH_POST:
                    searchPost();
                    break;
                case POSTS_BY_CATEGORY:
                    postsByCategory();
                    break;
                case ALL_POSTS:
                    allPosts();
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }


    }

    private static void addPost() {
        System.out.println("please input post data: title, text, category");
        String[] postData = scanner.nextLine().split(",");
        Date createdDate = new Date();
        Post post = new Post();
        post.setTitle(postData[0]);
        post.setText(postData[1]);
        post.setCategory(postData[2]);
        post.setCreatedDate(createdDate);
        postStorage.add(post);
        System.out.println("Post added");

    }

    private static void searchPost() {
        System.out.println("Please input keyword which you want to search");
        String keywordData = scanner.nextLine();
        postStorage.searchPostsByKeyword(keywordData);

    }

    private static void postsByCategory() {
        String categoryData = scanner.nextLine();
        postStorage.printPostsByCategory(categoryData);
    }

    private static void allPosts() {
        postStorage.printAllPosts();
    }


    private static void printCommands() {
        System.out.println("Please input " + EXIT + "  for exit");
        System.out.println("Please input " + ADD_POST + "  for add post");
        System.out.println("Please input " + SEARCH_POST + "  for search post");
        System.out.println("Please input " + POSTS_BY_CATEGORY + "  for search by category");
        System.out.println("Please input " + ALL_POSTS + "  for print all posts");
    }


}