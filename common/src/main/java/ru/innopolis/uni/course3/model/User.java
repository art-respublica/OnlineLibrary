package ru.innopolis.uni.course3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 *
 */
public class User implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String salt;
    private Date registered = new Date();
    private boolean enabled = true;
    private Set<Role> roles;

    private Integer version;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRegistered(), u.isEnabled(), u.getVersion(), u.getRoles());
    }

    public User(String name, String email, String password, Date registered, boolean enabled, Integer version, Set<Role> roles) {
        this(null, name, email, password, registered, enabled, version, roles);
    }

    public User(Integer id, String name, String email, String password, Date registered, boolean enabled, Integer version, Role role, Role... roles) {
        this(id, name, email, password, registered, enabled, version, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Date registered, boolean enabled, Integer version, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        this.version = version;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public boolean isAdmin(){
        boolean isAdmin = false;
        if (roles != null) {
            isAdmin = roles.contains(Role.ROLE_ADMIN);
        }
        return isAdmin;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}
