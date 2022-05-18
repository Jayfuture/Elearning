package in.bushansirgur.springbootthymeleaf.service;

import in.bushansirgur.springbootthymeleaf.dao.CustomerRepository;
import in.bushansirgur.springbootthymeleaf.entity.CustomUserDetails;
import in.bushansirgur.springbootthymeleaf.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public abstract class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepos;
    
    public UserDetails loadCustomerByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepos.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(customer);
    }

    @Transactional
    public boolean emailExists(String email){
        Customer findUser = customerRepos.findByEmail(email);
        return (findUser != null);
    }

    public String authenticateUsername(String username) {
        String errorMsg = "";
        if (username.length() < 3 || username.length() > 30){
            errorMsg = "Username needs to be between 3-30 characters.";
        }
        return errorMsg;
    }

    @Transactional
    public boolean usernameExists(String username){
        Customer findUser = customerRepos.findByUsername(username);
        return (findUser != null);
    }

    public List<String> authenticatePassword(String password) {
        ArrayList<String> errorMsgs = new ArrayList<>();

        if (password.length() < 8 || password.length() > 40){
            errorMsgs.add("Password length must be 8-40 characters.");
        }

        String upperCasePattern = ".*[A-Z].*";
        if (!password.matches(upperCasePattern)){
            errorMsgs.add("Password must contain at least one uppercase character.");
        }

        String lowerCasePattern = ".*[a-z].*";
        if (!password.matches(lowerCasePattern)){
            errorMsgs.add("Password must contain at least one lowercase character.");
        }

        String numbersSymbolsPattern = ".*[0-9|\\W].*";
        if (!password.matches(numbersSymbolsPattern)){
            errorMsgs.add("Password must contain at least one number or symbol.");
        }

        return errorMsgs;
    }

    public boolean checkConfirmPassword(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }
}