package blog.storage;
import blog.model.User;

public class UserStorageImpl implements UserStorage {
    private User[] users = new User[10];
    private int size;

    @Override
    public void add(User user) {
        if (size == users.length) {
            extend();
        }
        users[size++] = user;
    }

    private void extend() {
        User[] tmp = new User[users.length + 15];
        System.arraycopy(users, 0, tmp, 0, users.length);
        users = tmp;
    }

    @Override
    public Boolean isRegistered(String email, String password) {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)){
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean isDuplicate(String email, String password) {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }
}
