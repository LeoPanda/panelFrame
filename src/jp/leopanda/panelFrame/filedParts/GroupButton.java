package jp.leopanda.panelFrame.filedParts;

import com.google.gwt.user.client.ui.Button;

import jp.leopanda.panelFrame.enums.Error;

/**
 * グループフィールドにボタンを追加したい場合に使用するクラス
 */
public class GroupButton extends FieldBase<Button> implements FieldCommon {
  //コンストラクタ
  public GroupButton(String styleName,String label){
    super(styleName, "", null, new Button(label));
  }
  //クローン用コンストラクタ
  public GroupButton(GroupButton original) {
    super(original, new Button(original.basicFieldClass.getText()));
  }

  @Override
  public String getText() {
    return this.basicFieldClass.getText();
  }

  @Override
  public void setText(String text) {
    this.basicFieldClass.setText(text);
  }

  @Override
  public void setErr(Error err, String errMsg) {
  }

  @Override
  public boolean validate() {
    return true;
  }

  @Override
  public Error getErr() {
    return null;
  }

  @Override
  public String getErrMsg() {
    return null;
  }

  @Override
  public void popError() {
  }

  @Override
  public GroupButton clone() {
    return new GroupButton(this);
  }

  @Override
  protected String getFieldValue() {
    return null;
  }

}
