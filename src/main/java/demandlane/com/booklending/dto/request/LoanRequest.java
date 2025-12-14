package demandlane.com.booklending.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LoanRequest {
     private String bookId;
    private String memberId;
    private boolean allowed = true;
    private String errorMessage;
    private LocalDate dueDate; 
    private int loanDurationDays = 14;

}
