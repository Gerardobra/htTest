package it.aizoon.ersaf.bean.form;


import org.springframework.web.multipart.MultipartFile;
import it.aizoon.ersaf.form.BaseForm;


public class StampaDomandaForm extends BaseForm {

private MultipartFile fileStampa;

public MultipartFile getFileStampa() {
	return fileStampa;
}

public void setFileStampa(MultipartFile fileStampa) {
	this.fileStampa = fileStampa;
}
  }
