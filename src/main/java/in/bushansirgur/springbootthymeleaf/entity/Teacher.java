package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tblTeachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(nullable = false, unique = true, length = 30)
    private String teacherName;       //private final ThreadLocal<String> name = new ThreadLocal<String>();

    @Column(nullable = false, unique = true, length = 30)
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

}





