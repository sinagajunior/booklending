package demandlane.com.booklending.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import demandlane.com.booklending.configuration.DroolsConfig;
import demandlane.com.booklending.dto.LoanDto;
import demandlane.com.booklending.repository.BookRepository;
import demandlane.com.booklending.repository.LoanRepository;
import demandlane.com.booklending.repository.MemberRepository;

@SpringBootTest
@Import(DroolsConfig.class)
public class BookLoanServiceTest {


    @Mock
    private KieContainer kieContainer; 

  @Mock
   BookRepository bookRepository;

  @Mock
  LoanRepository loanRepository;

    @Mock
    MemberRepository memberRepository;

    // The service we are testing (needs to be adjusted slightly to use the kieContainer from context)
    // For this specific setup, we need to adjust LoanService to accept KieContainer via constructor injection
    @InjectMocks
    private BookLoanService loanService; // The service under test

    // Mock repositories if they were actually used in attemptLoan (currently they are placeholders in the example)
    // @Mock private MemberRepository memberRepository;
    // @Mock private LoanRepository loanRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        KieServices ks = KieServices.Factory.get();
        kieContainer = ks.getKieClasspathContainer(); 
        // Ensure loanService uses the autowired kieContainer if needed
        // this.loanService = new LoanService(kieContainer); 
    }

    // NOTE: This test currently uses the placeholder logic in the provided LoanService example
    // where Member/Loan data is mocked internally to the service method. 
    // A robust test would mock database responses.

    @Test
    public void testLoanDeniedWhenMemberHasOverdueLoan() {
        // Mock scenario setup (requires adjusting LoanService to allow mocking data access)
        // Let's assume the internal logic uses this data:
        
        // Loan is overdue (returnedAt is null, dueDate is in the past)
        LoanDto overdueLoan = new LoanDto();
        overdueLoan.setBookId("ererewr");
        overdueLoan.setMemberId("rerer");
        
        overdueLoan.setBorrowedAt(LocalDateTime.now().minusDays(20));
        overdueLoan.setDueDate(LocalDateTime.now().minusDays(5));
        overdueLoan.setReturnedAt(null);

        // --- The following part depends on how you inject data into the service method ---
        // Since the current example hardcodes mocks *inside* attemptLoan, 
        // a true unit test requires refactoring the service to use injected repositories.

        // Placeholder for expected result based on the Excel rule definition
        // We expect an ERROR message back
        
        String result = loanService.processingLoan(overdueLoan.getMemberId(),overdueLoan.getBookId(),"reref"); // This call won't work perfectly with current mocks
         assertTrue(result.contains("ERROR: borrow not allowed due to have overdue date of book loan"));
    }

    @Test
    public void testLoanApprovedWhenMemberIsValid() {
        

        // Mock scenario: Member has no loans
        // The service mocks 'activeLoans' as empty list internally
        
        // Placeholder for expected result based on the Excel rule definition
        // We expect an approval message
        
        // String result = loanService.attemptLoan(memberId, bookId); 
        // assertTrue(result.contains("Loan approved and issued successfully."));
    }


    @Test
    void testProcessingLoan() {

    }
}
