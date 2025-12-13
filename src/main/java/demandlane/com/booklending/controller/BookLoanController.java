package demandlane.com.booklending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demandlane.com.booklending.service.BookLoanService;


@RestController
@RequestMapping("/api/loans")
public class BookLoanController {

     private final BookLoanService loanService;


    public BookLoanController(BookLoanService loanService) {
        this.loanService = loanService;
    }

    // A simple request body DTO can be used here in a real app
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam String memberId, @RequestParam String bookId,@RequestParam String name) {
        // In a real application, you'd fetch the actual Member and Book entities here
        // The service layer currently uses mocks/placeholders for the DB interaction part.
        try {
            String resultMessage = loanService.processingLoan(memberId, bookId,name);
            if (resultMessage.startsWith("Loan denied:")) {
                return ResponseEntity.badRequest().body(resultMessage);
            }
            return ResponseEntity.ok(resultMessage);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }

}
