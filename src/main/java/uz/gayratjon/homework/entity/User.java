package uz.gayratjon.homework.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Mohirdev_user")   // User degan table ochib bo'lmaydi. Keyword bor user degan
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 50)
    @Column(length = 50, unique = true, nullable = false)   // uzunlik 50 tagacha, unique shart, null bo'lmaydi
    private String login;

    @NotNull
    @Size(min = 20, max = 60)
    @Column(length = 60, unique = true, nullable = false)   // uzunlik 50 tagacha, unique shart, null bo'lmaydi
    private String password;

    private String name;
    private String lastName;
    private String email;

    @NotNull
    @Column(nullable = false)
    private Boolean activated = false;

    private String langKey;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(                     // qaysi user ga qaysi role bog'lanishini saqlovchi jadval configuration lari
            name = "user_role",     // jadval nomi: user_role
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, // User table dan id olinadi
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")} // Role table dan name olinadi
            // Masalan: 1-user ADMIN, 2-user USER1, 3-user ADMIN etc ...
    )
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
