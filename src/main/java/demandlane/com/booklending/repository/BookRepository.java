package demandlane.com.booklending.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demandlane.com.booklending.domain.Book;

public interface BookRepository extends JpaRepository<Book, String> {


}
