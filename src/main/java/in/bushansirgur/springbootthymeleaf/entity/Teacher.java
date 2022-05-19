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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="tblteachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teacher implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private enum Role {
        CUSTOMER,
        TEACHER
    }

    @Column(nullable = false, unique = true, length = 30)
    private String teacherName;       //private final ThreadLocal<String> name = new ThreadLocal<String>();

    @Enumerated(EnumType.STRING)
    private Role role = Role.TEACHER;

    @Column(nullable = false, unique = true, length = 30)
    @Email
    private String teacherEmail;

    @Column(nullable = false, length = 100)
    private String teacherAddress;

    @Column
    private String income;

    @Column(nullable = false, length = 30)
    private String teacherPassword;

    @Transient
    private String confirmPassword;

    /*
    　one to many   step 1　　　首先在一端添加：
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "one") //mappedBy = "one" 表示one是一对多管理的被维护端， 既当添加many时顺带添加一个one
    private List<Many> manys;
*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<Course> courses;

    public Teacher(String teacherName, String teacherEmail, String teacherPassword, String confirmPassword) {
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPassword = teacherPassword;
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





