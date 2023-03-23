package pasteBox.pasteboxApp.exception;

/**
 * \* @author AleKras on 18.03.2023
 * \* Description:
 * \*
 */

public class NotFoundEntityException extends RuntimeException {
	public NotFoundEntityException(String s) {
		super(s);
	}
}