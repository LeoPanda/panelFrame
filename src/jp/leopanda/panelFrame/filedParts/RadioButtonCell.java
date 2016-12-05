package jp.leopanda.panelFrame.filedParts;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RadioButton;

/**
 * 単体RadioButton
 * RadioButtonFieldに埋め込んで使用される
 * @author LeoPanda
 *
 */
public class RadioButtonCell extends FieldBase<RadioButton> implements FieldCommon {

  protected RadioButtonCell(String styleName,String groupName){
    super(styleName, "", null, new RadioButton(groupName));
  }
  
  public Boolean getValue(){
    return basicFieldClass.getValue();
  }
  public void addClickHandler(ClickHandler handler){
    this.basicFieldClass.addClickHandler(handler);
  }
  public void setSelected(){
    this.basicFieldClass.setValue(true);
  }
  @Override
  public void setText(String text) {
    this.basicFieldClass.setTitle(text);
  }

  @Override
  public boolean validate() {
    return true;
  }

  /**
   * 常にnullが返されます
   */
  @Override
  @Deprecated
  public RadioButtonCell clone() {
    return null;
  }

  /**
   * 常にnullが返されます
   */
  @Override
  @Deprecated
  protected String getFieldValue() {
    return null;
  }

}
