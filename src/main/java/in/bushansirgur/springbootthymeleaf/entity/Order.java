package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tbl_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;

    @Column(nullable = false, unique = true)  // need to update
    private Date createDate;

    @Column(nullable = false, unique = true)
    private int quantity;

//    @Column(nullable = false, unique = true, length = 30)  // need to update
//    private Enum status;


    /*
    　one to many   step 1　　　首先在一端添加：
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "one") //mappedBy = "one" 表示one是一对多管理的被维护端， 既当添加many时顺带添加一个one
    private List<Many> manys;

        　　然后再多端添加：

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "one_id") // 在多端（从表的外键）添加外键字段指向一端（主表的主键）的主键字段
private One one;
*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")  // 在多端（从表的外键）添加外键字段指向一端（主表的主键）的主键字段
    private Customer customer;



  /*　many to many step 1     much to many   much为多方关系的维护端  在Much中添加：

    @ManyToMany
    @JoinTable(　　// JoinTable所在的一端为多方关系的维护端
            name = "much_more", // 指定中间表名
            joinColumns= {@JoinColumn(name = "many_id", referencedColumnName = "id")}, // 指定当前表在中间表的外键名称和外键所指向的当前表主键
            inverseJoinColumns= {@JoinColumn(name = "much_id", referencedColumnName = "id")} // 指定另一方在中间表的外键名称和外键所指向的主键
            　　　　　　　　　　)
    private List<Many> manys;
    */

    @ManyToMany
    @JoinTable(
            name = "orderCourse",
            joinColumns= {@JoinColumn(name = "oId", referencedColumnName = "orderId")},
            inverseJoinColumns= {@JoinColumn(name = "cId", referencedColumnName = "courseId")}
            )
    private List<Course> courses;

}
