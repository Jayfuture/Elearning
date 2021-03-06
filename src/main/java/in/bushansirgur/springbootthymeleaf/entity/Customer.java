package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="tblCustomers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private enum Role {
        CUSTOMER,
        TEACHER
    }

    @Column(nullable = false, length = 30)
    @NotEmpty(message="Username is mandatory")
    private String customerName;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;

    @Column(nullable = false, unique = true, length = 30)
    @NotEmpty (message="Email is mandatory")
    @Email
    private String customerEmail;

    @Column(nullable = true, length = 100)
    private String customerAddress;

    @Column(nullable = false, length = 30)
    @NotEmpty(message = "Password is mandatory")
    private String customerPassword;

    @Transient
    @NotEmpty(message = "Password is mandatory")
    private String confirmPassword;

        /*
    　one to many   step 1　　　首先在一端添加：
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "one") //mappedBy = "one" 表示one是一对多管理的被维护端， 既当添加many时顺带添加一个one
    private List<Many> manys;

        　　然后再多端添加：

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "one_id") // 在多端（从表的外键）添加外键字段指向一端（主表的主键）的主键字段
private One one;
*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> orders;

    public Customer(String customerName, String customerEmail, String customerPassword, String confirmPassword) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.confirmPassword = confirmPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return list;
    }

    @Override
    public String getPassword() {return null;}

    @Override
    public String getUsername() {return null;}

    @Override
    public boolean isAccountNonExpired() {return false;}

    @Override
    public boolean isAccountNonLocked() {return false;}

    @Override
    public boolean isCredentialsNonExpired() {return false;}

    @Override
    public boolean isEnabled() {return false;}

}

