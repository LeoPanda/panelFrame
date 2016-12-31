package jp.leopanda.panelFrame.filedParts;

import java.util.ArrayList;
import java.util.List;
import jp.leopanda.panelFrame.enums.Style;

import com.google.gwt.aria.client.OrientationValue;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 同一グループのラジオボタン群を一括処理するフィールド
 * 
 * @author LeoPanda
 *
 */
/**
 * @author LeoPanda
 *
 */
public class RadioButtonField extends FieldGroup implements FieldCommon, Cloneable {

  public Label label;
  private String groupName;
  protected CellPanel alignPanel;
  private OrientationValue direction;
  private List<RadioButtonCell> groupFieldList = new ArrayList<RadioButtonCell>();
  protected ListElement[] elements;
  private int cloneCounter;

  private EventAction actionListener;

  public void addEventListener(EventAction actionListener) {
    this.actionListener = actionListener;
  }

  /**
   * コンストラクタ
   * 
   * @param labelName String ラベル名
   * @param groupName String ラジオボタンのグループ名
   * @param elements ListElement[] ラジオボタンの凡例リスト
   * @param direction OrientationValue ラジオボタンの配列方向
   */
  public RadioButtonField(String labelName, String groupName, ListElement[] elements,
      OrientationValue direction) {
    basicSetup(labelName, groupName, direction, 0);
    this.elements = elements;
    generateRadioButtons();
    this.add(alignPanel);
    setSelectedIndex(0);
  }

  /**
   * リストエレメント拡張用コンストラクタ
   * 
   * @param labelName String ラベル名
   * @param groupName String ラジオボタンのグループ名
   * @param direction OrientationValue ラジオボタンの配列方向
   */
  public RadioButtonField(String labelName, String groupName, OrientationValue direction) {
    basicSetup(labelName, groupName, direction, 0);
  }

  /**
   * クローン用コンストラクタ
   * 
   * @param original 元のラジオボタンフィールド
   */
  public RadioButtonField(RadioButtonField original) {
    int newCloneCounter = original.cloneCounter + 1;
    String newGroupName = original.groupName + String.valueOf(newCloneCounter);
    basicSetup(original.label.getText(), newGroupName, original.direction, newCloneCounter);
    this.elements = original.elements;
    generateRadioButtons();
    this.add(alignPanel);
    setSelectedIndex(0);

  }

  // このフィールドのセットアップ
  private void basicSetup(String labelName, String groupName, OrientationValue direction,
      int cloneCounter) {
    this.label = new Label(labelName);
    this.label.addStyleName(Style.LABEL.getName());
    this.groupName = groupName;
    this.direction = direction;
    this.alignPanel = getGroupPanel(direction);
    this.cloneCounter = cloneCounter;
    this.add(this.label);
  }

  /**
   * ラベルのスタイルを設定する
   * 
   * @param style String ラベルのスタイル名
   */
  public void addLabelStyle(String style) {
    this.label.addStyleName(style);
  }

  // フィールドセルを格納するグループパネルを作成する
  private CellPanel getGroupPanel(OrientationValue direction) {
    CellPanel panelBase;
    switch (direction) {
      case HORIZONTAL:
        panelBase = new HorizontalPanel();
        break;
      case VERTICAL:
        panelBase = new VerticalPanel();
        break;
      default:
        panelBase = null;
        break;
    }
    return panelBase;
  }

  // ラジオボタン群を生成しグループパネルに格納する。
  protected void generateRadioButtons() {
    for (int i = 0; i < this.elements.length; i++) {
      HorizontalPanel cellField = new HorizontalPanel();
      cellField.add(generateRadioButton());
      cellField.add(setFieldLabel(i));
      alignPanel.add(cellField);
    }
  }

  /**
   * ラジオボタンのラベルを指定する
   * 
   * @param index int エレメントリスト上の位置
   */
  protected Widget setFieldLabel(int index) {
    Label label = new Label(this.elements[index].getName());
    label.setHorizontalAlignment(ALIGN_LEFT);
    return label;
  }

  // ラジオボタンをひとつ生成する
  private RadioButtonCell generateRadioButton() {
    RadioButtonCell radioButton = new RadioButtonCell(this.groupName);
    radioButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        RadioButtonField.this.actionListener.onValueChange();
      }
    });
    groupFieldList.add(radioButton);
    return radioButton;
  }

  /**
   * 選択されたラジオボタンの入力エレメント上の位置を返す
   * 
   */
  public int getSelectedIndex() {
    int selectedIndex = 0;
    int index = 0;
    for (RadioButtonCell radioButton : groupFieldList) {
      if (radioButton.getValue() == true) {
        selectedIndex = index;
        break;
      }
      index++;
    }
    return selectedIndex;
  }

  /**
   * クローンの作成
   */
  public RadioButtonField clone() {
    return new RadioButtonField(this);
  }

  /**
   * 指定されたインデックスのラジオボタンをチェック状態にする
   * 
   * @param index int ラジオボタンの位置
   */
  public void setSelectedIndex(int index) {
    groupFieldList.get(index).setSelected();
  }

  /**
   * 選択されたラジオボタンのエレメント値を返す
   */
  public String getText() {
    return elements[getSelectedIndex()].getName();
  }

  /**
   * 作用しません。
   */
  @Override
  @Deprecated
  public void setText(String text) {}
}
