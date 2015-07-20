package jp.leopanda.panelFrame.filedParts;

import java.util.Map;

import jp.leopanda.panelFrame.validate.ValidateBase;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;

/**
 * リストボックス入力フィールドを作成するクラス
 * 
 * @author LeoPanda
 *
 */
public class ListBoxField extends FieldBase<ListBox> implements FieldCommon {
  private Map<String, String> valueList; // 選択値のリストマップ
  private EventAction actionListener;

  public void addEventListener(EventAction actionListener) {
    this.actionListener = actionListener;
  }

  /**
   * コンストラクタ
   * 
   * @param styleName
   *          スタイル名
   * @param label
   *          ラベルに表示する文字列
   * @param valueList
   *          リストボックスに表示する選択リスト
   * @param validates
   *          バリデータの配列
   */
  public ListBoxField(String styleName, String label, ValidateBase[] validates,
      Map<String, String> valueList) {
    super(styleName, label, validates, new ListBox());
    basicFieldClass.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        actionListener.onValueChange();
      }
    });
    this.valueList = valueList;
    setListBox(this.valueList);
  }

  /**
   * クローン用コンストラクタ
   * 
   * @param original クローン元オブジェクト
   */
  private ListBoxField(ListBoxField original) {
    super(original, new ListBox());
    basicFieldClass.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        actionListener.onValueChange();
      }
    });
    this.valueList = original.valueList;
    this.valueList.remove(original.getFieldKey());// オリジナルで選択されている項目は除く
    setListBox(valueList);
  }

  /**
   * リストボックスに選択リストをセットする
   */
  public void setListBox(Map<String, String> valueList) {
    for (Map.Entry<String, String> value : valueList.entrySet()) {
      basicFieldClass.addItem(value.getKey(), value.getValue());
    }
  }

  /*
   * 選択された値を返す
   * @see jp.leopanda.panelFrame.filedParts.FieldBase#getFieldValue()
   */
  @Override
  protected String getFieldValue() {
    return basicFieldClass.getValue(basicFieldClass.getSelectedIndex());
  }

  /**
   * 選択されたキー値を返す
   */
  public String getFieldKey() {
    return basicFieldClass.getItemText(basicFieldClass.getSelectedIndex());
  }

  /*
   * 初期選択をキー値でセット
   * 
   * @see jp.leopanda.panelFrame.filedParts.FieldBase#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    for (int i = 0; i < basicFieldClass.getItemCount(); i++) {
      if (basicFieldClass.getItemText(i).equals(text)) {
        basicFieldClass.setSelectedIndex(i);
      }
    }
  }

  /*
   * 正当性のチェック
   */
  @Override
  public boolean validate() {
    return super.validate();
  }

  /* clone this.
   * @see jp.leopanda.panelFrame.filedParts.FieldBase#clone()
   */
  @Override
  public ListBoxField clone() {
    return new ListBoxField(this);
  }
}
