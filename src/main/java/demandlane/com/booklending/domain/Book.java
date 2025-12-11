package demandlane.com.booklending.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;


}
