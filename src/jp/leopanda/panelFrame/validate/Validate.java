package jp.leopanda.panelFrame.validate;

import jp.leopanda.panelFrame.enums.Error;

public interface Validate {
  boolean validate(String value);

  Error getError();

  String getErrMsg();

}
