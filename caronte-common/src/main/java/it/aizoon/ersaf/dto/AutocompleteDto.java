package it.aizoon.ersaf.dto;

/**
 * @author Ivan Morra
 *
 */
public class AutocompleteDto extends BaseDto {

  private static final long serialVersionUID = 1L;
  private String id;
  private String text;
  
  public AutocompleteDto(String id, String text) {
    this.id = id;
    this.text = text;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
