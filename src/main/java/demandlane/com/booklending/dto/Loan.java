package demandlane.com.booklending.dto;

import java.time.LocalDate;

public record Loan(String id, String bookId, String memberId, LocalDate borrowedAt, LocalDate dueDate, LocalDate returnedAt) {

}
