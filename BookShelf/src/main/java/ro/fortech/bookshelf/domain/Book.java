package ro.fortech.bookshelf.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * Used to define information to add/post a book for donation.
 * 
 * @author Gyuszika
 *
 */
public class Book extends AbstractModel {
	
	@NotEmpty
	private String bookTitle;

	@NotNull
	private int publicationYear;

	@NotEmpty
	private String author;

	@NotNull
	private int numberOfPages;
	
	@NotEmpty
	private String bookLanguage;
	
	@NotEmpty
	private String donatorEmail;
	
	@NotEmpty
	private String bookLocation;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getBookLanguage() {
		return bookLanguage;
	}

	public void setBookLanguage(String bookLanguage) {
		this.bookLanguage = bookLanguage;
	}

	public String getDonatorEmail() {
		return donatorEmail;
	}

	public void setDonatorEmail(String donatorEmail) {
		this.donatorEmail = donatorEmail;
	}

	public String getBookLocation() {
		return bookLocation;
	}

	public void setBookLocation(String bookLocation) {
		this.bookLocation = bookLocation;
	}

	@Override
	public String toString() {
		return "Book [bookTitle=" + bookTitle + ", publicationYear=" + publicationYear + ", author=" + author
				+ ", numberOfPages=" + numberOfPages + ", bookLanguage=" + bookLanguage + ", donatorEmail="
				+ donatorEmail + ", bookLocation=" + bookLocation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookLanguage == null) ? 0 : bookLanguage.hashCode());
		result = prime * result + ((bookLocation == null) ? 0 : bookLocation.hashCode());
		result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
		result = prime * result + ((donatorEmail == null) ? 0 : donatorEmail.hashCode());
		result = prime * result + numberOfPages;
		result = prime * result + publicationYear;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookLanguage == null) {
			if (other.bookLanguage != null)
				return false;
		} else if (!bookLanguage.equals(other.bookLanguage))
			return false;
		if (bookLocation == null) {
			if (other.bookLocation != null)
				return false;
		} else if (!bookLocation.equals(other.bookLocation))
			return false;
		if (bookTitle == null) {
			if (other.bookTitle != null)
				return false;
		} else if (!bookTitle.equals(other.bookTitle))
			return false;
		if (donatorEmail == null) {
			if (other.donatorEmail != null)
				return false;
		} else if (!donatorEmail.equals(other.donatorEmail))
			return false;
		if (numberOfPages != other.numberOfPages)
			return false;
		if (publicationYear != other.publicationYear)
			return false;
		return true;
	}

}
