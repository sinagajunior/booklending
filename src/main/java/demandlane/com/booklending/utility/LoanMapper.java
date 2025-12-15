package demandlane.com.booklending.utility;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import demandlane.com.booklending.domain.Book;
import demandlane.com.booklending.domain.Loan;
import demandlane.com.booklending.domain.Member;
import demandlane.com.booklending.dto.BookDto;
import demandlane.com.booklending.dto.LoanDto;
import demandlane.com.booklending.dto.MemberDto;
import demandlane.com.booklending.dto.request.LoanRequest;

@Mapper
public interface LoanMapper {



@Mapping(source = "totalCopies", target = "totalCopies")
@Mapping(source = "availableCopies", target = "availableCopies")
@Mapping(source = "id", target = "id")
@Mapping(source = "title", target = "title")
@Mapping(source = "author", target = "author")
BookDto toBookDto(Book book);



//  private String bookId;
//     private String memberId;
//     private LocalDate borrowedAt;
//     private LocalDate dueDate;
//     private LocalDate returnedAt;




@Mapping(source = "id", target = "id")
@Mapping(source = "bookId", target = "bookId")
@Mapping(source = "memberId", target = "memberId")
@Mapping(source = "borrowedAt", target = "borrowedAt")
@Mapping(source = "dueDate", target = "dueDate")
@Mapping(source = "returnedAt", target = "returnedAt")
LoanDto toLoanDto(Loan loan);


@Mapping(source = "id", target = "id")
@Mapping(source = "name", target = "name")
@Mapping(source = "email", target = "email")
MemberDto toMemberDto(Member member);

@Mapping(source = "totalCopies", target = "totalCopies")
@Mapping(source = "availableCopies", target = "availableCopies")
@Mapping(source = "id", target = "id")
@Mapping(source = "title", target = "title")
@Mapping(source = "author", target = "author")
Book toBook(BookDto bookDto);


@Mapping(source = "id", target = "id")
@Mapping(source = "bookId", target = "bookId")
@Mapping(source = "memberId", target = "memberId")
@Mapping(source = "borrowedAt", target = "borrowedAt")
@Mapping(source = "dueDate", target = "dueDate")
@Mapping(source = "returnedAt", target = "returnedAt")
Loan toLoan(LoanDto loanDto);


@Mapping(source = "id", target = "id")
@Mapping(source = "name", target = "name")
@Mapping(source = "email", target = "email")
Member toMember(MemberDto memberDto);

//    private String bookId;
//     private String memberId;
//     private boolean allowed = true;
//     private String errorMessage;
//     private LocalDate dueDate; 

@Mapping(source = "bookId", target = "bookId")
@Mapping(source = "memberId", target = "memberId")
@Mapping(source = "dueDate", target = "dueDate")    
LoanRequest toLoanRequest(LoanDto loanDto);

    


}
