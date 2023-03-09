package cs3500.modelcommands;

import java.util.ArrayList;
import java.util.List;

/**
 * The invoker class for the model command pattern.
 */
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class ModelInvoker {

  private final List<ModelOperation> operationList = new ArrayList<>();

  public void executeOperation(ModelOperation operation) {
    operationList.add(operation);
    operation.execute();
  }
}
