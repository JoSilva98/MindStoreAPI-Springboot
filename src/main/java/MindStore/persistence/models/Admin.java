package MindStore.persistence.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@ToString
@Entity
@Table(name = "admins")
public class Admin extends Person{
    public Admin() {
        super();
    }
}
