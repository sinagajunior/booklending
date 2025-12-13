package demandlane.com.booklending.service;


i
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demandlane.com.booklending.domain.Loan;
import demandlane.com.booklending.domain.Member;
import demandlane.com.booklending.repository.BookRepository;
import demandlane.com.booklending.repository.LoanRepository;
import demandlane.com.booklending.repository.MemberRepository;

@Service
public class BookLoanService {

private final KieContainer kieContainer;

@Autowired
BookRepository bookRepository;

@Autowired
LoanRepository loanRepository;

@Autowired
MemberRepository memberRepository;


@Autowired    
public BookLoanService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }
 
    
public String processingLoan(String memberId,String bookId,String name){
Member member = new Member();
member.setId(memberId);
member.setName(name);
List<Loan> loans = new ArrayList<>();

KieSession kieSession = kieContainer.newKieSession("bookLoanKS");
kieSession.insert(member);

for(Loan loan:loans){
    kieSession.insert(loan);
}






return "Loan Approved";

}
}
