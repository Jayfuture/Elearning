package in.bushansirgur.springbootthymeleaf.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Customer customer;

    public CustomUserDetails(Customer customer) {
        this.customer = customer;
    }

    public Customer getUser() {return customer; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return customer.getCustomerPassword();
    }

    @Override
    public String getUsername() {
        return customer.getCustomerName();
    }

    public String getEmail() {
        return customer.getCustomerEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}