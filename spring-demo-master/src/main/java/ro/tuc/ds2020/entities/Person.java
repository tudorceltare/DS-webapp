package ro.tuc.ds2020.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "role", nullable = true)
    private String role;

    // mapping to SmartDevices
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    private Set<SmartDevice> smartDevices;

    // used for CREATE
    public Person(String name,
                  String username,
                  String address,
                  String password,
                  int age,
                  String email,
                  String role,
                  Set<SmartDevice> smartDevices) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.password = password;
        this.age = age;
        this.email = email;
        this.role = role;
        this.smartDevices = smartDevices;
    }

    // used for UPDATE
    public Person(UUID id,
                  String name,
                  String username,
                  String address,
                  String password,
                  int age,
                  String email,
                  String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.address = address;
        this.password = password;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    // custom SET SmartDevices
    public void setSmartDevices(Set<SmartDevice> smartDevices) {
        this.smartDevices = smartDevices;
        for (SmartDevice s : smartDevices){
            s.setPerson(this);
        }
    }


    // JWT security
//    @Column(name = "authorities", nullable = true)
//    private String[] authorities;
//    @Column(name = "lastLoginDate", nullable = true)
//    private Date lastLoginDate;
//    @Column(name = "joinDate", nullable = true)
//    private Date joinDate;
//    @Column(name = "roles", nullable = true)
//    private String[] roles;
//
//
//    public Person(UUID id, String name, String address, String password, int age, String email, Date lastLoginDate, Date joinDate, String[] roles, String[] authorities) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.password = password;
//        this.age = age;
//        this.email = email;
//        this.lastLoginDate = lastLoginDate;
//        this.joinDate = joinDate;
//        this.roles = roles;
//        this.authorities = authorities;
//    }

}