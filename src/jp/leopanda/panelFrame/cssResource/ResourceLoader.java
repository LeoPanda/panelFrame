package jp.leopanda.panelFrame.cssResource;

import javax.inject.Inject;

/**
 * @author LeoPanda
 *
 */
public class ResourceLoader {
  @Inject
  ResourceLoader(StandardStyle standardStyle) {
    standardStyle.style().ensureInjected();
  }
}
