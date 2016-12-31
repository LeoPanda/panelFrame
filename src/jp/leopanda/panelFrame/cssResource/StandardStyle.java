package jp.leopanda.panelFrame.cssResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Standard CSS resources
 * 
 * @author LeoPanda
 *
 */
public interface StandardStyle extends ClientBundle {
  StandardStyle INSTANCE = GWT.create(StandardStyle.class);

  interface Style extends CssResource {
    String numeric();

    String remarks();
    
    String warning();
    
    String label();
  }

  Style style();
}
