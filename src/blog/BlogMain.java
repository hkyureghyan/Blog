package blog;


import blog.storage.PostStorageImpl;
import blog.model.Post;
import blog.storage.UserStorageImpl;
import blog.model.User;

import java.util.Date;
import java.util.Scanner;

public class BlogMain {
    private static final int EXIT = 0;
    private static final int LOGOUT = 0;
    private static final int LOGIN = 1;
    private static final int REGISTER = 2;
    private static final int ADD_POST = 1;
    private static final int SEARCH_POST = 2;
    private static final int POSTS_BY_CATEGORY = 3;
    private static final int ALL_POSTS = 4;
    private static final int Get_BY_TITLE = 5;
    private static Scanner scanner = new Scanner(System.in);
    private static PostStorageImpl postStorage = new PostStorageImpl();
    private static UserStorageImpl userStorage = new UserStorageImpl();
    private static boolean isRun = true;
    private static boolean isRunLogin = true;
    private static Boolean isLogin = false;

    public static void main(String[] args) {
        int command;
        while (isRunLogin) {
            if (isLogin == false) {
                userCommandsWhenLogout();

                try {
                    command = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    command = -1;

                }

                switch (command) {
                    case LOGIN:
                        login();
                        break;
                    case REGISTER:
                        register();
                        break;
                    default:
                        System.out.println("Wrong command");

                }
            } else {
                userCommandsWhenLogin();

                try {
                    command = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    command = -1;

                }

                if (command == LOGOUT) {
                    System.out.println("You are logout");
                    isRunLogin = false;

                }
            }
        }
    }


    private static void userCommandsWhenLogin() {
        System.out.println("please input " + LOGOUT + " for logout");

    }

    private static void userCommandsWhenLogout() {
        System.out.println("please input " + LOGIN + " for login");
        System.out.println("please input " + REGISTER + " for registration");
    }


    private static void login() {
        if (userStorage.getSize() > 0) {
            System.out.println("Please input your email");
            String email = scanner.nextLine();
            System.out.println("Please input your password");
            String password = scanner.nextLine();
            if (userStorage.isRegistered(email, password)) {
                System.out.println("Successfully login");
                isLogin = true;
                postSection();
            } else if (userStorage.getSize() == 0) {
                System.out.println("There is no any user please sign up at first");
                register();

            } else {
                System.out.println("Incorrect email or password please input correct data");
                login();
            }
        } else {
            System.out.println("Please sign up at first for login");
            register();
        }


    }

    private static void register() {
        System.out.println("Input user data please name,surname,email,password");
        String[] userData = scanner.nextLine().split(",");

        if (userData.length == 4) {
            if (userStorage.isDuplicate(userData[2], userData[3])) {
                System.out.println("This type of user with same email " +
                        "and password already exist please try again");
                register();
            } else {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                userStorage.add(user);
                System.out.println("You are successfully registered");
            }
        } else {
            System.out.println("Incorrect input format");
            register();
        }
    }

    private static void postSection() {
        int command;

        while (isRun) {
            try {
                printCommands();
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
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
                case Get_BY_TITLE:
                    getByTitle();
                    break;
                default:
                    System.out.println("Wrong command");
            }


        }
    }

    private static void addPost() {
        String[] postData = null;

        try {
            System.out.println("please input post data: title, text, category");
            postData = scanner.nextLine().split(",");


        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input correct format");
            addPost();

        }
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
        if (postStorage.isExist()) {
            System.out.println("Please input keyword which you want to search");
            String keywordData = scanner.nextLine();
            postStorage.searchPostsByKeyword(keywordData);
        } else {
            System.out.println("Nothing for search please input post at first");
        }

    }

    private static void postsByCategory() {
        if (postStorage.isExist()) {
            String categoryData = scanner.nextLine();
            postStorage.printPostsByCategory(categoryData);
        } else {
            System.out.println("Nothing for search please input post at first");
        }
    }

    private static void allPosts() {
        if (postStorage.isExist()) {
            postStorage.printAllPosts();
        } else {
            System.out.println("Nothing for print please input the post at first");
        }
    }


    private static void printCommands() {
        System.out.println("Please input " + EXIT + "  for exit");
        System.out.println("Please input " + ADD_POST + "  for add post");
        System.out.println("Please input " + SEARCH_POST + "  for search post");
        System.out.println("Please input " + POSTS_BY_CATEGORY + "  for search by category");
        System.out.println("Please input " + ALL_POSTS + "  for print all posts");
        System.out.println("Please input " + Get_BY_TITLE + "  for get  post by title");
    }

    private static void getByTitle() {
        System.out.println("Please input title for search");
        String titleData = scanner.nextLine();
        System.out.println(postStorage.getPostByTitle(titleData));
    }


}
