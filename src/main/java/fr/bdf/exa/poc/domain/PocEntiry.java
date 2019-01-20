package fr.bdf.exa.poc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author I21186E
 *
 */
@Entity
public class PocEntiry {

    @Id
    private Integer id;
    
    private String name;
    private Date entry;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getEntry() {
        return entry;
    }
    
    public void setEntry(Date entry) {
        this.entry = entry;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Poc [name=");
        builder.append(name);
        builder.append(", entry=");
        builder.append(entry);
        builder.append("]");
        return builder.toString();
    }
    
    
}
