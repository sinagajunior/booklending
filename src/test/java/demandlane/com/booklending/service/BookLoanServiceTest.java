// package demandlane.com.booklending.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.Date;
// import java.util.List;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.kie.api.runtime.KieContainer;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.context.annotation.Import;

// import demandlane.com.booklending.configuration.DroolsConfig;
// import demandlane.com.booklending.dto.Book;
// import demandlane.com.booklending.dto.Loan;
// import demandlane.com.booklending.dto.Member;
// import demandlane.com.booklending.dto.request.LoanRequest;
// import demandlane.com.booklending.repository.BookRepository;
// import demandlane.com.booklending.repository.LoanRepository;
// import demandlane.com.booklending.repository.MemberRepository;


// @Import(DroolsConfig.class)
// public class BookLoanServiceTest {

//     // 2. Mock the repository dependencies
//     @Mock
//     private BookRepository bookRepository;
//     @Mock
//     private LoanRepository loanRepository;
//     @Mock
//     private MemberRepository memberRepository;

//      @Mock   
//      KieContainer kieContainer;


//     // 3. Inject mocks and the real KieContainer into a real instance of the service
//     @InjectMocks
//     private BookLoanService bookLoanService;
    
//     // Initialize Mockito annotations before each test
//     @BeforeEach
//     public void setUp() {
//         // We initialize mocks manually because we aren't using Spring's test runner
//         MockitoAnnotations.openMocks(this); 
        
//         // Manual injection of the KieContainer since it's not a mock
//         // This is a slight hack but works perfectly for this specific setup
//         // The @InjectMocks might handle this automatically in recent Mockito versions if the field name matches the constructor argument name
//         // but to be explicit:
//         // this.bookLoanService = new BookLoanService(kieContainer, bookRepository, loanRepository, memberRepository);
//         // The @InjectMocks line above should wire the fields automatically.
//     }

//     // Helper data setup
//     private final Member testMember = new Member("M001", "Alice Smith", "alice@example.com");
//     private final Book testBook = new Book("B005", "New Book Title", "Author", "ISBN123", 5, 2);
//     private final Date now = new Date();

//     @Test
//     void testLoanRequestAllowedWhenWithinLimits() {
//         // Arrange: Member has 1 active loan, limit is 3 (defined in the DRL service)
//         LocalDate tomorrow = LocalDate.now().plusDays(1);
//         LocalDate today = LocalDate.now();
//         List<Loan> currentLoans = Collections.singletonList(
//            new Loan("L001", "B001", "M001", today, tomorrow, null)
//         );

//         // Act
//         LoanRequest result = bookLoanService.evaluateLoanRequest(testMember, testBook, currentLoans);

//         // Assert
//         assertTrue(result.isAllowed());
//         assertNull(result.getErrorMessage());
//         assertNotNull(result.getDueDate());
//         // Check if the calculated due date is 14 days in the future (based on service config)
//         assertEquals(LocalDate.now().plusDays(14), result.getDueDate());
//     }
//     @Test
//     void testLoanRequestBlockedWhenMaxLoansExceeded() {
//         // Arrange: Member has 3 active loans, limit is 3. A 4th request should fail.
//         LocalDate futureDate = LocalDate.now().plusDays(5);
//         LocalDate today = LocalDate.now();
//         List<Loan> currentLoans = Arrays.asList(
//            new Loan("L001", "B001", "M001", today, futureDate, null),
//            new Loan("L002", "B002", "M001", today, futureDate, null),
//             new Loan("L003", "B003", "M001", today, futureDate, null)
//         );
        

//         // Act
//         LoanRequest result = bookLoanService.evaluateLoanRequest(testMember, testBook, currentLoans);

//         // Assert
//         assertFalse(result.isAllowed());
//         assertEquals("You cannot have more than 3 active loans.", result.getErrorMessage());
//         assertNull(result.getDueDate(), "Due date should not be set if the loan is disallowed.");
//     }
//     @Test
//     void testLoanRequestBlockedWhenOverdueLoanExists() {
//         // Arrange: Member has 1 overdue loan (due date was yesterday)
//         LocalDate yesterday = LocalDate.now().minusDays(1);
//         LocalDate today = LocalDate.now();
//         List<Loan> currentLoans = Collections.singletonList(
//             new Loan("L001", "B001", "M001", today, yesterday, null)
//         );
        
//         // Add another valid loan to ensure both rules are handled by salience correctly
//          List<Loan> loansWithOverdue = new ArrayList<>(currentLoans);
//          loansWithOverdue.add(new Loan("L002", "B002", "M001", today, LocalDate.now().plusDays(2), null));


//         // Act
//         LoanRequest result = bookLoanService.evaluateLoanRequest(testMember, testBook, loansWithOverdue);

//         // Assert
//         assertFalse(result.isAllowed());
//         // This specific message comes directly from the DRL file's action block
//         assertEquals("You cannot borrow new books while you have overdue loans.", result.getErrorMessage());
//         assertNull(result.getDueDate(), "Due date should not be set if the loan is disallowed.");
//     }
// }
