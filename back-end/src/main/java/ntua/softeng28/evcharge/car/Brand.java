package ntua.softeng28.evcharge.car;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brand {
    private @Id String id;
    private String name;

    public Brand(){
        
    }

    public Brand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "Brand [id=" + id + ", name=" + name + "]";
    }
}
