package mate.academy.internetshop.model;

import java.util.Objects;

public class Role {
    private RoleName roleName;

    public Role() {

    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public enum RoleName {
        USER, ADMIN
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return roleName == role.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }

    @Override
    public String toString() {
        return "Role{" + "roleName=" + roleName + '}';
    }
}
