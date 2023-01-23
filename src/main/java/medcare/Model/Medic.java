package medcare.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Medic {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;
    private String telephone;
    private String specialization;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "medic")
    private Set<Prescription> prescriptions;

    public Medic(String name, String surname, String password, String telephone, Set<Prescription> prescriptions, String specialization) {
        this.username = name + "." + surname;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.telephone = telephone;
        this.prescriptions = prescriptions;
        this.specialization = specialization;
    }

    public Medic(String name,String surname, String password) {
        this.username = name + "." + surname;
        this.name = name;
        this.surname = surname;
    }

    public Medic(String username, String password){
        this.username = username;
        this.password = password;
    }

    public Medic() {}

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }


    public String toString(){
        return "doc." + this.surname + " (" + this.specialization + ")";
    }

    public boolean equals(Medic med) {
        return this.username == med.getUsername() && this.password == med.getPassword();
    }
}
