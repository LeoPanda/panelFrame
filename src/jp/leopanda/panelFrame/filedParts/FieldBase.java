package jp.leopanda.panelFrame.filedParts;

import jp.leopanda.panelFrame.enums.Error;
import jp.leopanda.panelFrame.enums.Style;
import jp.leopanda.panelFrame.validate.Validate;
import jp.leopanda.panelFrame.validate.ValidateBase;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * 入力フィールドを生成するクラスのベースとなる抽象クラス <<機能>> ・ラベルの配置 ・個別スタイル名のセット
 * 
 * @author LeoPanda
 *
 */
public abstract class FieldBase<T extends FocusWidget> extends HorizontalPanel implements
    FieldCommon, Cloneable {
  private final String DEFAULT_STYLE = "FieldBase"; // デフォルトのCSSスタイル名
  private Label label; // ラベル
  private String styleName; // スタイル名
  private ValidateBase[] validates; // validateインスタンスの配列
  protected Error err = Error.NOTHING; // フィールドのエラー状態
  protected String errMsg = Error.NOTHING.getMsg(); // フィールドのエラーメッセージ
  protected T basicFieldClass; // 入力部品の総称クラス

  /**
   * コンストラクタ
   * 
   * @param fieldAttr
   *          各入力パネルで定義するフィールド属性のenum値
   */
  protected FieldBase(String styleName, String label, ValidateBase[] validates, T basicFieldClass) {
    this.styleName = styleName;
    this.label = new Label(label);
    this.validates = validates;
    this.basicFieldClass = basicFieldClass;
    setStyle(styleName);
    this.add(this.label);
    this.add(this.basicFieldClass);
    setRequierdStyle();
  }

  /**
   * クローン用コンストラクタ
   */
  protected FieldBase(FieldBase<T> original, T basicFieldClass) {
    this.label = new Label(original.label.getText());
    this.styleName = new String(original.styleName);
    this.validates = original.validates;
    this.basicFieldClass = basicFieldClass;
    setStyle(styleName);
    this.add(this.label);
    this.add(this.basicFieldClass);
    setRequierdStyle();
  }

  /*
   * validate配列をセットする
   */
  public void setValidate(ValidateBase[] validates) {
    this.validates = validates;
    setRequierdStyle();
  }

  /*
   * スタイルの設定
   */
  private void setStyle(String name) {
    label.addStyleName(name);
    this.addStyleName(DEFAULT_STYLE);
    this.addStyleName(name);
    basicFieldClass.addStyleName(name);
  }

  /*
   * エラー時のアクション
   */
  public void popError() {
    basicFieldClass.setFocus(true);
    Window.alert(this.errMsg);
  }

  /*
   * 入力値のgetter
   */
  public String getText() {
    return getFieldValue();
  }

  /*
   * err値のgetter
   */
  public Error getErr() {
    return err;
  }

  /*
   * エラーメッセージを取得
   */
  public String getErrMsg() {
    return this.errMsg;
  }

  /*
   * ラベルの非表示
   */
  public void disableLabel() {
    label.setVisible(false);
  }

  /*
   * ラベルの表示
   */
  public void enableLabel() {
    label.setVisible(true);
  }

  /*
   * 必須入力時のスタイルをセットする
   */
  private void setRequierdStyle() {
    if (validates == null) {
      return;
    }
    for (Validate validate : this.validates) {
      if (validate.getError() == Error.REQUIRED) {
        Label required = new Label("(必須)");
        required.addStyleName(Style.REMARKS.getName());
        this.add(required);
      }
    }
  }

  /**
   * エラーのスタイルセットする
   */
  public void setErr(Error err, String errMsg) {
    this.err = err;
    this.errMsg = errMsg;
    if (err == Error.NOTHING) {
      this.basicFieldClass.removeStyleName(Style.WARNING.getName());
    } else {
      this.basicFieldClass.addStyleName(Style.WARNING.getName());
    }
  }

  /**
   * 正当性チェック
   */
  public boolean validate() {
    if (validates == null) {
      return true;
    }
    for (Validate validator : validates) {
      if (!validator.validate(getText())) {
        setErr(validator.getError(), validator.getErrMsg());
        return false;
      }
    }
    setErr(Error.NOTHING, Error.NOTHING.getMsg());
    return true;
  }

  /*
   * 入力部品具象クラスから入力値を得る
   * 
   * 具象化した入力部品から、それぞれの部品のメソッドで 入力された値を取り出す方法を記述。
   */
  protected abstract String getFieldValue();

  /*
   * フィールドに初期値をセットする
   */
  public abstract void setText(String text);

  /*
   * フィールドのクローンを作成する
   */
  public abstract FieldBase<T> clone();
}
