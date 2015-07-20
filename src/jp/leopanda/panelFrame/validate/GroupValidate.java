package jp.leopanda.panelFrame.validate;

import java.util.List;

import jp.leopanda.panelFrame.enums.Error;
import jp.leopanda.panelFrame.filedParts.FieldCommon;

public interface GroupValidate {
  boolean validate(List<FieldCommon> fieldList);

  Error getError();

  String getErrMsg();

}
