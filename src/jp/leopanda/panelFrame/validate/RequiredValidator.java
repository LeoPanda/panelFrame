package jp.leopanda.panelFrame.validate;

import jp.leopanda.panelFrame.enums.Error;

/**
 * 必須入力チェック
 */
public class RequiredValidator extends ValidateBase implements Validate {

  public RequiredValidator() {
    super(Error.REQUIRED);
  }

  @Override
  public boolean validate(String value) {
    if (value.length() > 0) {
      return true;
    }
    return false;
  }

}
