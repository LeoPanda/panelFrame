package jp.leopanda.panelFrame.filedParts;

import jp.leopanda.panelFrame.validate.ValidateBase;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;

/**
 * リストボックス入力フィールドを作成するクラス リストの 値はListElementインターフェースを実装したenumによって指定する。
 * 具体的な実装方法はhtmlEditHelperを参照のこと。
 * 
 * @author LeoPanda
 *
 */
public class ListBoxField extends FieldBase<ListBox> implements FieldCommon {
  private EventAction actionListener;

  public void addEventListener(EventAction actionListener) {
    this.actionListener = actionListener;
  }

  /**
   * コンストラクタ
   * 
   * @param label
   *          ラベルに表示する文字列
   * @param validates
   *          バリデータの配列
   * @param elements
   *          選択値の配列
   */
  public ListBoxField(String label, ValidateBase[] validates, ListElement[] elements) {
    super(label, validates, new ListBox());
    basicFieldClass.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        if (actionListener != null) {
          actionListener.onValueChange();
        }
      }
    });
    setListBox(elements);
  }

  /**
   * クローン用コンストラクタ
   * 
   * @param original
   *          クローン元オブジェクト
   */
  private ListBoxField(ListBoxField original) {
    super(original, new ListBox());
    basicFieldClass.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        if (actionListener != null) {
          actionListener.onValueChange();
        }
      }
    });
    for (int i = 0; i < original.basicFieldClass.getItemCount(); i++) {
      basicFieldClass.addItem(original.basicFieldClass.getItemText(i));
    }
  }

  /**
   * リストボックスに選択リストをセットする
   */
  public void setListBox(ListElement[] elements) {
    for (ListElement element : elements) {
      basicFieldClass.addItem(element.getName());
    }
  }

  /**
   * リストボックスの選択リストをクリアする
   */
  public void clear() {
    basicFieldClass.clear();
  }

  /*
   * 選択された値を返す
   * 
   * @see jp.leopanda.panelFrame.filedParts.FieldBase#getFieldValue()
   */
  @Override
  protected String getFieldValue() {
    return basicFieldClass.getValue(basicFieldClass.getSelectedIndex());
  }

  /**
   * 選択されたインデックス値を返す
   */
  public int getSelectedIndex() {
    return basicFieldClass.getSelectedIndex();
  }

  /*
   * 初期選択をセット
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

  /* 
   * 
   */
  @Override
  public ListBoxField clone() {
    return new ListBoxField(this);
  }

}
