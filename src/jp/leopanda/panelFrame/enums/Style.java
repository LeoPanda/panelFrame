package jp.leopanda.panelFrame.enums;


import jp.leopanda.panelFrame.cssResource.StandardStyle;

/**
 * 共通スタイル名
 * 
 * @author LeoPanda
 *
 */
public enum Style {
  WARNING(StandardStyle.INSTANCE.style().warning()), 
  REMARKS(StandardStyle.INSTANCE.style().remarks()), 
  NUMERIC(StandardStyle.INSTANCE.style().numeric()),
  LABEL(StandardStyle.INSTANCE.style().label());

  private String name;

  Style(String name) {
    StandardStyle.INSTANCE.style().ensureInjected();
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
