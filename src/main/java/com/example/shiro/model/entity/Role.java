package com.example.shiro.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author fengqian
 * @since <pre>2018/07/03</pre>
 */
@Entity
@Table(name = "t_role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    private String description;
    private boolean available;
    @Transient
    private List<String> privileges;

}
