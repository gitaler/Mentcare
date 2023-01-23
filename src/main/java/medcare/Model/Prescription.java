package medcare.Model;

import javax.persistence.*;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    //auto-generata
    private String date;
    private String drug;
    private String quantity;
    private String description;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Medic medic;
    @OneToOne(cascade = {CascadeType.PERSIST})
    private Patient patient;

    public Prescription(String date, String drug, String quantity, String description, Medic medic, Patient patient){
        this.date = date;
        this.drug = drug;
        this.quantity = quantity;
        this.description = description;
        this.medic = medic;
        this.patient = patient;
    }

    public Prescription() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Medic  getMedic() {
        return medic;
    }

    public void setMedic(Medic  medic) {
        this.medic = medic;
    }

    public String toString(){
        return getDrug() + " " + getQuantity() + ": \n" + getDescription() + " \n " + getMedic();
    }

    public Patient getPatient() {return patient;}

    public void setPatient(Patient patient) {this.patient = patient;}
}
