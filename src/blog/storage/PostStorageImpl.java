package blog.storage;

import blog.model.Post;

public class PostStorageImpl implements PostStorage {
    private Post[] posts = new Post[10];
    private int size;

    @Override
    public void add(Post post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    private void extend() {
        Post[] tmp = new Post[posts.length + 15];
        System.arraycopy(posts, 0, tmp, 0, posts.length);
        posts = tmp;
    }

    public Post getPostByTitle(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                return post;
            }
        }
        return null;
    }

    public void searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().contains(keyword) || posts[i].getText().contains(keyword)) {
                System.out.println(posts[i]);
            }
        }
    }

    public void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }
    }

    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getCategory().equals(category)){
                System.out.println(posts[i]);
            }
        }
    }

}
