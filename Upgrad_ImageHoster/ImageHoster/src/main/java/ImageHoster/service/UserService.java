package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }

    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    // Following method Added by Amit Hiran
    // Following method under UserService check the Strength of Password
    // Method checks that password has atleast 1 alphabet, 1 number & 1 special character
    public boolean passwordStrenthCheck (String password) {
        String checkPassword;
        checkPassword = password;

        boolean number = false;
        boolean alpha = false;
        boolean specialChar = false;

        // Following for loop checks if password has atleast one number
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                number = true; // if Yes set number = true
                break; // Break out if found
            }
        }

        // Following for loop checks if password has atleast one alphabet
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                alpha = true;
                break;
            }
        }

        // Following for loop checks if password has atleast one Special Character
        for (int i = 0; i < password.length(); i++) {
            if (!(Character.isDigit(password.charAt(i))) && !(Character.isLetter(password.charAt(i)))) {
                specialChar = true;
                break;
            }
        }

        // Following IF condition checks if all conditions 1 alphabet, 1 number & 1 special character are true

        if (number == true && alpha == true && specialChar == true) {
            return true; // if all true - return true
        } else {
            return false; // if any of the condition false - return false
        }
    }
}


