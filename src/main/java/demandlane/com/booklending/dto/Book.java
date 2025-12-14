package demandlane.com.booklending.dto;

public record Book(String id, String title, String author, String isbn, int totalCopies, int availableCopies) {

}
