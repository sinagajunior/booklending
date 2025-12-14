package demandlane.com.booklending.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demandlane.com.booklending.domain.Loan;
import demandlane.com.booklending.dto.Book;
import demandlane.com.booklending.dto.Member;
import demandlane.com.booklending.dto.request.LoanRequest;
import demandlane.com.booklending.service.BookLoanService;




@RestController
@RequestMapping("/api/loans")
public class BookLoanController {

  @Autowired
  BookLoanService loanService;

    // A simple request body DTO can be used here in a real app
    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam String memberId, @RequestParam String bookId) {
        // In a real application, you'd fetch the actual Member and Book entities here
        // The service layer currently uses mocks/placeholders for the DB interaction part.
   
            Member member = new Member(memberId,"sinaga","sinagajunior@gmail.com"); // Placeholder

            
            
            LoanRequest resultMessage = loanService.evaluateLoanRequest(member);
           
            return ResponseEntity.ok(resultMessage.toString());
       
    }

}
