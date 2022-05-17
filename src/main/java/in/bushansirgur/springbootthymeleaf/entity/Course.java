package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;

@Entity
@Table(name="tbl_courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;

    @Column(nullable = false, unique = true, length = 30)
//    @NotEmpty(message="Course name is mandatory.")
//    @Size(min=1, max=30, message="Course name has to be 1-30 characters.")
    private String course_name;

    @Column(nullable = true, length = 4000)
//    @NotEmpty(message="Content cannot be empty.")
//    @Size(min=10, max=4000, message="Content must be between 10-4000 characters long.")
    private String description;

    @Column(nullable = false) // need to update
    private String img;

    @Column(nullable = false)
    private Double price;

//   @Column(nullable = false, length = 30)
//    private String teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id") // 在多端（从表的外键）添加外键字段指向一端（主表的主键）的主键字段
    private Teacher teacher;

//
/*　many to many step 2 然后在多方关系的被维护端Many中添加：
    @ManyToMany(mappedBy="manys") // mappedBy指定many为多方关系的被维护端
    private List<Much> muchs;
　　此时生成的表结构是：生成一个中间表叫much_more， 外键字段叫much_id，more_id分别指向两个表的主键
 */
@ManyToMany(mappedBy="courses")
private List<Order> orders;

}
