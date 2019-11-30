package net.anumbrella.oauth2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.anumbrella.oauth2.util.GuidGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther anumbrella
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    /**
     * Database id
     */
    protected int id;

    /**
     * guid.
     */
    protected String guid = GuidGenerator.generate();

    private String username;

    private String password;

    private String phone;

    private String email;

    protected int enable;

    //Default user is initial when create database, do not delete
    private boolean defaultUser = false;

    private Date lastLoginTime;

    private List<Privilege> privileges = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInfo)) {
            return false;
        }
        UserInfo that = (UserInfo) o;
        return guid.equals(that.getGuid());
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }
}
