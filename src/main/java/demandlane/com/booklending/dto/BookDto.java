package demandlane.com.booklending.dto;

public record BookDto(String id, String title, String author, String isbn, int totalCopies, int availableCopies) {

}
