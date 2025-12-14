package demandlane.com.booklending.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demandlane.com.booklending.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, String> {
    List<Loan> findByMemberId(String memberId);


}
