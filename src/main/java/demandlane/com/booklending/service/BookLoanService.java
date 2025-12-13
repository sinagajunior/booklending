package demandlane.com.booklending.service;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demandlane.com.booklending.dto.LoanDto;
import demandlane.com.booklending.dto.MemberDto;
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



 
    
public String processingLoan(String memberId,String bookId,String name){
MemberDto member = new MemberDto();
member.setId(memberId);
member.setName(name);
member.setEmail(name+"@email.com");
member.setName(name);
List<LoanDto> loans = new ArrayList<>();
 LoanDto overdueLoan = new LoanDto();
        overdueLoan.setBookId("ererewr");
        overdueLoan.setMemberId("rerer");
        
        overdueLoan.setBorrowedAt(LocalDateTime.now().minusDays(20));
        overdueLoan.setDueDate(LocalDateTime.now().minusDays(5));
        overdueLoan.setReturnedAt(null);
    loans.add(overdueLoan);    

KieSession kieSession = kieContainer.newKieSession();
kieSession.insert(member);

for(LoanDto loan:loans){
    kieSession.insert(loan);
}





return "Loan Approved";


}

}
