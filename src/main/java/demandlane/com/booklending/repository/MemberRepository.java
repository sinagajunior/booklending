package demandlane.com.booklending.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demandlane.com.booklending.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    
} 