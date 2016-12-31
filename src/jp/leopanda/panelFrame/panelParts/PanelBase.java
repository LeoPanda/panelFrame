package jp.leopanda.panelFrame.panelParts;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 入力パネルの共通機能定義
 * 
 * @author LeoPanda
 *
 */
public abstract class PanelBase extends VerticalPanel {
  private final int fieldMapStep = 100; // フィールドマップindexの刻み幅
  /*
   * 入力フィールドコレクションマップ
   */
  protected FieldMap fieldMap = new FieldMap(fieldMapStep);

  /*
   * デフォルトのコンストラクタ
   */
  protected PanelBase() {
  }

  /*
   * 入力チェック 複合チェックが必要な場合は実装クラスで上書きしてください。
   */
  public boolean validateFields() {
    return fieldMap.validate();
  }

}