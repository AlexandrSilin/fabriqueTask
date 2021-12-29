package ru.task.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Quiz> userQuiz = new HashSet<>();

    @Column(name = "role")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CompleteQuiz> quizPassed = new ArrayList<>();

    public User() {

    }

    public User(Long id, String username, String password, Set<Quiz> userQuiz, List<Role> roles,
                List<CompleteQuiz> quizPassed) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userQuiz = userQuiz;
        this.roles = roles;
        this.quizPassed = quizPassed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Quiz> getUserQuiz() {
        return userQuiz;
    }

    public void setUserQuiz(Set<Quiz> userQuiz) {
        this.userQuiz = userQuiz;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<CompleteQuiz> getQuizPassed() {
        return quizPassed;
    }

    public void setQuizPassed(List<CompleteQuiz> quizPassed) {
        this.quizPassed = quizPassed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && username.equals(user.username) &&
                password.equals(user.password) &&
                userQuiz.equals(user.userQuiz) &&
                roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
