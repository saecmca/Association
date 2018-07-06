package com.association.registration;

import com.association.common.StatusOf;

import java.util.List;

public class LangResp {
  public StatusOf getStatus() {
    return status;
  }

  public void setStatus(StatusOf status) {
    this.status = status;
  }

  StatusOf status;

  public List<LangModel> getLanguage_Details_List() {
    return Language_Details_List;
  }

  public void setLanguage_Details_List(List<LangModel> language_Details_List) {
    Language_Details_List = language_Details_List;
  }

  List<LangModel> Language_Details_List;
}
