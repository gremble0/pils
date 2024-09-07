package pils.runtime.objects;

public class UncallableException extends RuntimeException {
  public UncallableException(String callee) {
    super(String.format("symbol '%s' is not callable", callee));
  }
}
