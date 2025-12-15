package demandlane.com.booklending.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demandlane.com.booklending.domain.Book;
import demandlane.com.booklending.domain.Loan;
import demandlane.com.booklending.domain.Member;
import demandlane.com.booklending.dto.BookDto;
import demandlane.com.booklending.dto.LoanDto;
import demandlane.com.booklending.dto.MemberDto;
import demandlane.com.booklending.dto.request.LoanRequest;
import demandlane.com.booklending.repository.BookRepository;
import demandlane.com.booklending.repository.LoanRepository;
import demandlane.com.booklending.repository.MemberRepository;
import demandlane.com.booklending.utility.LoanMapper;

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

   

    

    public LoanRequest evaluateLoanRequest(MemberDto member) {
        KieSession kieSession = kieContainer.newKieSession();
        List<LoanDto> currentLoans = new ArrayList<>(); // Placeholder for current loans
        kieSession.insert(member);
        // kieSession.insert(book);
        currentLoans.forEach(kieSession::insert);
        LoanRequest request = new LoanRequest();
        request.setMemberId(member.id());
        request.setBookId("sample-book-id");
        kieSession.insert(request);

        kieSession.fireAllRules();
        kieSession.dispose();

        return request;
    }

    public void recordLoan(LoanDto loanDto) {
        LoanMapper mapper = Mappers.getMapper(LoanMapper.class);
        // Persist the loan using the repository
        Loan savedLoan = mapper.toLoan(loanDto);
        loanRepository.save(savedLoan);
    }

    public void recordBookReturn(String loanId) {
        // Fetch the loan from the repository
        demandlane.com.booklending.domain.Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan != null) {
            // Update the loan status to returned
            // loan.setReturned(true);
            loanRepository.save(loan);
        }

    }

    public List<LoanDto> getMemberLoans(String memberId) {
        List<LoanDto> loans = new ArrayList<>();
        List<demandlane.com.booklending.domain.Loan> loanEntities = loanRepository.findByMemberId(memberId);
        for (demandlane.com.booklending.domain.Loan loanEntity : loanEntities) {
            // Loan loan = new Loan(
            // loanEntity.getId(),
            // loanEntity.getBookId(),
            // loanEntity.getMemberId(),
            // loanEntity.getBorrowedAt(),
            // loanEntity.getDueDate()
            // );
            // loans.add(loan);

        }

        return loans;

    }




    public void recordBook(BookDto bookDto) {
        LoanMapper loanMapper = Mappers.getMapper(LoanMapper.class);
         
        Book savedBook = loanMapper.toBook(bookDto);
        bookRepository.save(savedBook);
    }

    public void registerMember(MemberDto memberDto) {
        LoanMapper mapper = Mappers.getMapper(LoanMapper.class);
        Member savedMember = mapper.toMember(memberDto);
        memberRepository.save(savedMember);
    }





}
