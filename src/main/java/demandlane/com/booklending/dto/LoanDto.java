package demandlane.com.booklending.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoanDto {
    private String bookId;
    private String memberId;
    private LocalDateTime borrowedAt;
    private LocalDateTime dueDate;
    private LocalDateTime returnedAt;
    private boolean overdue;

}
