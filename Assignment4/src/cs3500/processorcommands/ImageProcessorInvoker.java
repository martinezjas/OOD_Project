package cs3500.processorcommands;

import java.util.ArrayList;
import java.util.List;

/**
 * The invoker class for the processor command pattern.
 */
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class ImageProcessorInvoker {
  private final List<ImageProcessorOperation> operationList = new ArrayList<>();

  public void executeOperation(ImageProcessorOperation operation) {
    operationList.add(operation);
    operation.execute();
  }
}
