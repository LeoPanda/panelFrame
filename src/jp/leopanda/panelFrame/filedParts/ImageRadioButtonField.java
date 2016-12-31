package jp.leopanda.panelFrame.filedParts;

import com.google.gwt.aria.client.OrientationValue;
import com.google.gwt.user.client.ui.Widget;

/**
 * 凡例がイメージ表記のラジオボタンフィールド
 * @author LeoPanda
 *
 */
public class ImageRadioButtonField extends RadioButtonField {
  protected ListImageElement[] imageElements;

  /**
   * コンストラクタ
   * 
   * @param labelName String ラベル名
   * @param groupName String ラジオボタンのグループ名
   * @param elements ListImageElement[] ラジオボタンの凡例イメージ配列
   * @param direction OrientationValue ラジオボタンの整列方向
   */
  public ImageRadioButtonField(String labelName, String groupName, ListImageElement[] elements,
      OrientationValue direction) {
    super(labelName, groupName, direction);
    this.elements = elements;
    this.imageElements = elements;
    generateRadioButtons();
    this.add(alignPanel);
    setSelectedIndex(0);

  }

  @Override
  protected Widget setFieldLabel(int index) {
    return this.imageElements[index].getImage();
  }
}
