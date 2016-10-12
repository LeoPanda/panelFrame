package jp.leopanda.panelFrame.validate;

import jp.leopanda.panelFrame.enums.Error;

/**
 * 数値チェック
 * 
 * @author LeoPanda
 *
 */
public class IntegerValidator extends ValidateBase implements Validate {

  public IntegerValidator() {
    super(Error.INTEGER);
  }

  @Override
  public boolean validate(String value) {
    try {
      Integer.parseInt(value);
    } catch (NumberFormatException exception) {
      return false;
    }
    return true;
  }

}
