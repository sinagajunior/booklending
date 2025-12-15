package demandlane.com.booklending.dto;

import java.time.LocalDate;

public record LoanDto(String id, String bookId, String memberId, LocalDate borrowedAt, LocalDate dueDate, LocalDate returnedAt) {

}
