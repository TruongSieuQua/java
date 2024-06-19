package partterns.solid.singleresponsibility;

public class UserService {

    private UserStore userStore = new UserStore();

    public void saveUser(User user) {
        userStore.store(user);
    }

}
