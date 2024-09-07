package pils.runtime.objects;

import java.util.List;

abstract public class PilsObject {
  public PilsObject evalFunctionCall(List<PilsObject> arguments) {
    throw new UncallableException(this.toString());
  }
}
