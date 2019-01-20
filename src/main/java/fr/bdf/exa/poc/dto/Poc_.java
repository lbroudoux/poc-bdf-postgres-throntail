package fr.bdf.exa.poc.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author I21186E
 *
 */
public class Poc_ implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -154606981182094133L;
    
    private Long id;
    
    private String name;
    private Date entry;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
