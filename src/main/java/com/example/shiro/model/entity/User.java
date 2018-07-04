package com.example.shiro.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author fengqian
 * @since <pre>2018/07/03</pre>
 */
@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 42L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    private String salt;
    private boolean locked;
}
