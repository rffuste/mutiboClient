package courseracapstone.org.mutibo;

import com.google.common.base.Objects;

/**
 * Created by Ruben on 05/11/2014.
 */
public class MutiboUser {
    private long id;
    private String name;
    private String password;

    public MutiboUser() {
        super();
    }


    public MutiboUser(String name, String password) {
        super();
        this.name = name;
        this.password = password;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
    // TODO Auto-generated method stub
        if (obj instanceof SetQuestion) {
            MutiboUser other = (MutiboUser) obj;
            // Google Guava provides great utilities for equals too!
            return Objects.equal(this.name, other.name);
        } else {
        return false;
        }
    }

    @Override
    public int hashCode() {
    // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "MutiboUser [id=" + id + ", userid=" + name + ", password="
                + password + "]";
    }

}
