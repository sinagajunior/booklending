package demandlane.com.booklending.service;



import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demandlane.com.booklending.dto.Book;
import demandlane.com.booklending.dto.Loan;
import demandlane.com.booklending.dto.Member;
import demandlane.com.booklending.dto.request.LoanRequest;
import demandlane.com.booklending.repository.BookRepository;
import demandlane.com.booklending.repository.LoanRepository;
import demandlane.com.booklending.repository.MemberRepository;

@Service
public class BookLoanService {

 @Autowired   
 KieContainer kieContainer;

@Autowired
BookRepository bookRepository;

@Autowired
LoanRepository loanRepository;

@Autowired
MemberRepository memberRepository;


 // Configuration values previously in Excel config table
    private final int MAX_ACTIVE_LOANS = 3;
    private final int LOAN_DURATION_DAYS = 14;
 
    
public LoanRequest evaluateLoanRequest(Member member) {
        KieSession kieSession = kieContainer.newKieSession();

        // Set the global variables accessible by the DRL rules
        //kieSession.setGlobal("maxActiveLoans", MAX_ACTIVE_LOANS);
        //kieSession.setGlobal("loanDurationDays", LOAN_DURATION_DAYS);
        List<Loan> currentLoans = new ArrayList<>(); // Placeholder for current loans

        // Insert facts
        //Book book = new Book(); // Placeholder book, in real app fetch from DB
        kieSession.insert(member);
        //kieSession.insert(book);
        currentLoans.forEach(kieSession::insert);

        LoanRequest request = new LoanRequest();
        request.setMemberId(member.id());
        request.setBookId("sample-book-id");
        // Note: loanDurationDays in Request POJO is redundant now, but kept for consistency
        kieSession.insert(request);

        kieSession.fireAllRules();
        kieSession.dispose();

        return request;
    }

}
