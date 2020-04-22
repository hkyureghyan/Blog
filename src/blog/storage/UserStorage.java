package blog.storage;

import blog.model.User;

public interface UserStorage {
    void add(User user);

   Boolean isRegistered(String email, String password);
   int getSize();
   boolean isDuplicate(String email,String password);

}
