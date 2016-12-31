package jp.leopanda.panelFrame.filedParts;

import jp.leopanda.panelFrame.validate.ValidateBase;

import com.google.gwt.user.client.ui.TextArea;

/**
 * テキストエリア入力フィールドを作成するクラス
 * 
 * @author LeoPanda
 *
 */
public class TextAreaField extends FieldBase<TextArea> implements FieldCommon {
  /**
   * コンストラクタ
   * 
   * @param label ラベルに表示する文字列
   * @param validates バリデータの配列
   */
  public TextAreaField(String label, ValidateBase[] validates) {
    super(label, validates, new TextArea());
  }

  /**
   * クローン用コンストラクタ
   */
  private TextAreaField(TextAreaField original) {
    super(original, new TextArea());
  }

  /*
   * フィールドのgetter
   */
  @Override
  protected String getFieldValue() {
    return basicFieldClass.getText();
  }

  /*
   * 初期値のsetter
   */
  @Override
  public void setText(String text) {
    basicFieldClass.setText(text);
  }

  /*
   * フィールドのクローンを生成する
   */
  @Override
  public FieldBase<TextArea> clone() {
    return new TextAreaField(this);
  }
}
