package medcare.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Patient{

    //Primary Key Matricola del tipo #021801
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String telephone;
    private Integer age;
    //Malattia come lista di stringhe in caso di più diagnosi
    private String diagnosis;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient")
    private Set<Prescription> prescriptions;
    private String facility;
    private String ward;

    //terapie
    public Patient(String name, String surname, String password, String telephone,Integer age, String diagnosis, Set<Prescription> prescriptions, String facility, String ward) {
        this.username = name + "." + surname;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.telephone = telephone;
        this.age = age;
        this.diagnosis = diagnosis;
        this.prescriptions = prescriptions;
        this.facility = facility;
        this.ward = ward;
    }

    public Patient(String name, String surname, String password, String telephone, Integer age, String facility, String ward) {
        this.username = name + "." + surname;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.telephone = telephone;
        this.age = age;
        this.facility = facility;
        this.ward = ward;
    }

    public Patient(String name, String surname, String password, String telephone, Integer age, String diagnosis, String facility, String ward) {
        this.username = name + "." + surname;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.telephone = telephone;
        this.age = age;
        this.diagnosis = diagnosis;
        this.facility = facility;
        this.ward = ward;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Patient(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Patient () {};

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getCompleteName() {
        return name+" "+surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Override
    public String toString() {
        return this.name + this.surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals(Patient pat) {
        return this.username == pat.getUsername() && this.password == pat.getPassword();
    }
}
