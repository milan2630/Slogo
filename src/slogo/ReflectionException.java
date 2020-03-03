package slogo;

import java.util.ResourceBundle;

/**
 * Custom Reflection Exception from lecture
 *
 * @author Robert C. Duvall
 */
public class ReflectionException extends RuntimeException {

  // for serialization
  private static final long serialVersionUID = 1L;
  private static final String ERROR_FILE_PATH = "resources.ErrorMessages.ReflectionErrorMessages";
  private static final ResourceBundle errorResources = ResourceBundle.getBundle(ERROR_FILE_PATH);

  /**
   * Create an exception based on an issue in our code.
   */
  public ReflectionException(String property, Object... values) {
    super(String.format(errorResources.getString(property), values));
  }

}
