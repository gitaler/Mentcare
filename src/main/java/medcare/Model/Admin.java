package medcare.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;

    public Admin(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Admin(String name,String surname, String password) {
        this.username = name + "." + surname;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public Admin() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String toString(){
        return this.username + this.password;
    }

    public boolean equals(Admin admin) {
        return this.username == admin.getUsername() && this.password == admin.getPassword();
    }
}
