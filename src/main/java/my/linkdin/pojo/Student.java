/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author mac
 */
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    private String email;
    private String password;
    private String name;
//    @Transient
//    private CommonsMultipartFile resumefile;
    private String resume;
    private String originresume;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();
//    private Set<Interview> interviews;
//    private Set<Offer> offers;

    public Student() {
    }

    public String getOriginresume() {
        return originresume;
    }

    public void setOriginresume(String originresume) {
        this.originresume = originresume;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

//    public CommonsMultipartFile getResumefile() {
//        return resumefile;
//    }
//
//    public void setResumefile(CommonsMultipartFile resumefile) {
//        this.resumefile = resumefile;
//    }
//
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
    
    
    
}
