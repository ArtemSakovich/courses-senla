package com.company.hoteladministrator.model;

import com.company.hoteladministrator.model.generic.AEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends AEntity {

    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {
        users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Role)) {
            return false;
        }

        Role role = (Role) o;

        return role.name.equals(name) &&
                role.users.equals(users);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Role: " + name + "; Users: " + users;
    }
}

