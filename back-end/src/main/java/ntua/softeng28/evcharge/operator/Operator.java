package ntua.softeng28.evcharge.operator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Operator {
    @JsonProperty("ID")
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @JsonProperty("Title")
    private @Column(unique = true) String name;

    public Operator() {
    }

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

    @Override
    public String toString() {
        return "Operator [id=" + id + ", name=" + name + "]";
    }

    
}
