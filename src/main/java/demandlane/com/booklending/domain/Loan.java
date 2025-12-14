package demandlane.com.booklending.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "loans")
@Data
public class Loan {

private String id;
    @Id
    private String bookId;
    private String memberId;
    private LocalDate borrowedAt;
    private LocalDate dueDate;
    private LocalDate returnedAt;

}