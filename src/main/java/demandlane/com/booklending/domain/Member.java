package demandlane.com.booklending.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "members")
@Data
public class Member {
    @Id
    private String id;
    private String name;
    private String email;

}
