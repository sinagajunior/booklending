package demandlane.com.booklending.controller;

import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demandlane.com.booklending.dto.BookDto;
import demandlane.com.booklending.dto.MemberDto;
import demandlane.com.booklending.dto.request.LoanRequest;
import demandlane.com.booklending.service.BookLoanService;




@RestController
@RequestMapping("/api/loans")
public class BookLoanController {

  @Autowired
  BookLoanService loanService;

   
    @PostMapping("/borrowbook")
    public ResponseEntity<String> borrowBook(@RequestParam String memberId, @RequestParam String bookId) {
   
            MemberDto member = new MemberDto(memberId,"sinaga","sinagajunior@gmail.com"); 
            LoanRequest resultMessage = loanService.evaluateLoanRequest(member);
            return ResponseEntity.ok(resultMessage.toString());
       
    }


     @PostMapping("/registermember")
    public ResponseEntity<String> registerMember(@RequestBody MemberDto memberDto) {
             loanService.registerMember(memberDto);            
            return ResponseEntity.ok(HttpResponse.BodyHandlers.ofString().toString());
       
    }

      @PostMapping("/recordbook")
    public ResponseEntity<String> recordBook(@RequestBody BookDto bookDto) {
             loanService.recordBook(bookDto);         
            return ResponseEntity.ok(HttpResponse.BodyHandlers.ofString().toString());
       
    }

   




}
