package ims.domain.exceptions;

public class ForeignKeyViolationException extends DomainException 
{
    private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public ForeignKeyViolationException() {
	}

	/**
	 * @param s
	 */
	public ForeignKeyViolationException(String s) {
		super(s);
	}

	public ForeignKeyViolationException(Throwable cause) {
		super(cause);
	}

	public ForeignKeyViolationException(String s, Throwable cause) {
		super(s, cause);
	}
}
