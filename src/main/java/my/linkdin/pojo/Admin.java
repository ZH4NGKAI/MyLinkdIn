/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.pojo;

//import java.util.List;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
/**
 *
 * @author mac
 */
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    
    private String password;
    
    private String email;
    
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "admin_id")
//    List<Company> companies;
//    
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "admin_id")
//    List<Company> new_companies;
    
    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
