package partterns.solid.singleresponsibility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UserController {

    private final UserService persistenceService = new UserService();

    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userJson, User.class);

        UserValidator validator = new UserValidator();
        boolean valid = validator.validateUser(user);

        if(!valid) {
            return "ERROR";
        }

        persistenceService.saveUser(user);

        return "SUCCESS";
    }

}
