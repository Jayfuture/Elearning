package in.bushansirgur.springbootthymeleaf.controller;

import in.bushansirgur.springbootthymeleaf.dao.CustomerRepository;
import in.bushansirgur.springbootthymeleaf.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class CustomerController {

    @Autowired
    CustomerRepository customerRepos;


    @GetMapping("/register")
    ModelAndView registerUser() {
        ModelAndView mav = new ModelAndView("pages/user/register");
        Customer newCustomer = new Customer();
        mav.addObject("customer", newCustomer);
        return mav;
    }

    @PostMapping("/register")
    String saveCustomer( @ModelAttribute Customer customer) {
        // Check if username exists
//        if (customerService.usernameExists(customer.getCustomerName())) {
//            result.addError(new FieldError("user", "username", "Username already exists, please choose another one"));
//        }
//
//        // Authenticate username
//        String errorMsg = customerService.authenticateUsername(customer.getCustomerName());
//        if (!errorMsg.isEmpty()) {
//            result.addError(new FieldError("user", "username", errorMsg));
//        }
//
//        // Check if email exists
//        if (customerService.emailExists(customer.getCustomerEmail())) {
//            result.addError(new FieldError("user", "email", "Email already exists, please choose another email"));
//        }
//
//        // Authenticate password
//        List<String> errorMsgs = customerService.authenticatePassword(customer.getCustomerPassword());
//        if (!errorMsgs.isEmpty()) {
//            for (String msg : errorMsgs) {
//                result.addError(new FieldError("user", "password", msg));
//            }
//        }
//
//        // Check if both passwords are the same
//        if (!customerService.checkConfirmPassword(customer.getCustomerPassword(), customer.getConfirmPassword())) {
//            result.addError(new FieldError("user", "confirmPassword", "The 2 passwords don't match."));
//        }
//
//        if (result.hasErrors()) {
//            return "register-user-form";
//        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getCustomerPassword());
        customer.setCustomerPassword(encodedPassword);
        customerRepos.save(customer);
        return "pages/user/register-success";
    }

    @GetMapping("/login")
    ModelAndView loginUser() {
        ModelAndView mav = new ModelAndView("pages/user/login");
        Customer newCustomer = new Customer();
        mav.addObject("customer", newCustomer);
        return mav;
    }
}