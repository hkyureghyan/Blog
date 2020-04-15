package blog.storage;
import blog.model.Post;

public interface PostStorage {
    void add (Post post);
    Post getPostByTitle(String title);
    void searchPostsByKeyword(String keyword);
    void printAllPosts();
    void printPostsByCategory(String category);

}
