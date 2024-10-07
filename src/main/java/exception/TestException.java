package exception;

public class TestException extends Exception {


  public TestException(Throwable cause) {
    super(cause);
  }

  public TestException(String message, Throwable cause) {
    super(message, cause);
  }

  public TestException(String message) {
    super(message);
  }

  public TestException() {
    super();
  }
}
