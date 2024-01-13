package services;

import models.Address;
import models.User;

import java.util.HashMap;

public class UserService {
    private HashMap<String, User> userHashMap; // <userId, User>

    public User createUser(String email, String name, String password, Address address, String phoneNumber) {
        validatePassword(password);
        User newUser = new User(email, name, password, address, phoneNumber);
        userHashMap.put(newUser.getUserId(), newUser);
        return newUser;
    }

    public User logInUser(String userId, String password) {
      User user = getUser(userId);
      if(user.getPassword() == password) {
        return user;
      }
        // <todo> - make a ValidationException
        throw new RuntimeException("Password is Incorrect for userID : " + userId);
    }

    public User getUser(String userId) {
        User user = userHashMap.get(userId);
        if(user != null) {
          return user;
        }

        // user is not present, throwing exception
        // <todo> - make a ValidationException
        throw new RuntimeException("UserId is not present : " + userId);
    }

    private void validatePassword(String password) throws RuntimeException {
        // <todo> - make a ValidationException
        if(password.length() < 8) throw new RuntimeException("Password should be at least 8 characters long");
        // can write more validations such as password should contain one uppercase and one numeral
    }
}



//    Address address = new Address("mallespalya", "Bangalore", "Karnataka", "India", 560037);
//    User newUser = new User("kartikgoel362@gmail.com", "Kartik Goel", "randomPassword", address);
