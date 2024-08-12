import org.apache.commons.lang3.NotImplementedException;
import treestructure.BookNode;

import java.util.Stack;

/**
 * Application to test traversing Binary Trees and Binary Search Trees.
 *
 * Represents a Library with a collection of books.
 */
public class LibraryService {

    /**
     * The root node of our tree of Books.
     * Assume this tree is sorted by ISBN.
     * The tree and its nodes should not be modified by our application.
     */
    private final BookNode books;

    /**
     * Constructs our library with a default tree of books.
     * Assume this tree is sorted by ISBN.
     *
     * @param books The root node of a tree of Books
     */
    public LibraryService(final BookNode books) {
        this.books = books;
    }

    /**
     * Determines whether or not a book is in the library
     * by searching our tree for a book with the given ISBN.
     *
     * @param isbn A given ISBN to search our library for
     * @return True if a book with the given ISBN is in our library and
     * false otherwise
     */
    public boolean isBookInLibraryByIsbn(String isbn) {
        // PARTICIPANTS: IMPLEMENT YOUR BINARY SEARCH HERE
        if (isbn == null || isbn.isEmpty()) {
            return false;
        }
        BookNode iter = books;

        while (iter != null) {
            if (iter.getBook().getIsbn().compareTo(isbn) > 0) {
                iter = iter.getLeft();
            } else if (iter.getBook().getIsbn().equals(isbn)) {
                return true;
            } else {
                iter = iter.getRight();
            }
        }
        return false;
    }


    /**
     * Determines whether or not a book is in the library
     * by searching our tree for a book with the given Title and Author.
     *
     * @param title  A given title to search our library for, alongside an author's name
     * @param author The name of a given author to search our library for, alongside a title
     * @return True if a book with the given title and author is in our library, and
     * false otherwise
     */
    public boolean isBookInLibraryByTitleAndAuthor(String title, String author) {
        // Check for null or empty title/author
        if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
            return false;
        }

        // Create a stack to hold nodes to be searched
        Stack<BookNode> stack = new Stack<>();
        stack.push(books); // Assuming 'books' is the root node of the tree

        // While there are nodes in the stack
        while (!stack.isEmpty()) {
            // Remove the next node from the stack
            BookNode currentNode = stack.pop();

            // If the current node is the target, return success
            if (currentNode != null && isMatch(currentNode, title, author)) {
                return true;
            }

            // If the current node is not null, put its child nodes on the stack
            if (currentNode != null) {
                // Push right child first so left child is processed next
                stack.push(currentNode.getRight());
                stack.push(currentNode.getLeft());
            }
        }

        // Stack becomes empty, return failure
        return false;
    }
    private boolean isMatch(BookNode bookNode, String title, String author){
        return bookNode.getBook().getTitle().equals(title)
                && bookNode.getBook().getAuthor().equals(author);
    }

}
