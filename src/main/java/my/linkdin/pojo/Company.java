/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author mac
 */
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String description;
    private Boolean verification;
    
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private List<Job> jobs = new ArrayList<>();
    
//    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    private Set<Application> applications;

    public Company() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getVerification() {
        return verification;
    }

    public void setVerification(Boolean verification) {
        this.verification = verification;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Job> getJobs() {
        return jobs;
    }
    
//    public Set<Application> getApplications() {
//        return applications;
//    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    
}
