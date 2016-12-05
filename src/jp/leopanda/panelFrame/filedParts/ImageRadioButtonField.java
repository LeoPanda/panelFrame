package jp.leopanda.panelFrame.filedParts;

import com.google.gwt.aria.client.OrientationValue;
import com.google.gwt.user.client.ui.Widget;

public class ImageRadioButtonField extends RadioButtonField {
protected ListImageElement[] imageElements;
  /**
   * コンストラクタ
   * @param styleName
   * @param labelName
   * @param groupName
   * @param orientationValue
   */
  public ImageRadioButtonField(String styleName, String labelName, String groupName,
      ListImageElement[] elements,OrientationValue orientationValue) {
    super(styleName, labelName, groupName,orientationValue);
    this.elements = elements;
    this.imageElements = elements;
    generateRadioButtons();
    this.add(alignPanel);
    setSelectedIndex(0);

  }
  @Override
  protected Widget setFieldLabel(int i){
    return this.imageElements[i].getImage();
  }
}
