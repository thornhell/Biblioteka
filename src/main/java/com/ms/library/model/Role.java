package com.ms.library.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "librarytablerole")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private long roleId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "roleName")
    private String roleName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE,
            mappedBy = "roles")
    private List<User> users;

    public Role() {
    }

    public Role(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getRoleId() {

        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
