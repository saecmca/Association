package com.association.registration;

import com.association.common.StatusOf;

import java.util.List;

public class AssnResp {
  public StatusOf getStatus() {
    return status;
  }

  public void setStatus(StatusOf status) {
    this.status = status;
  }

  StatusOf status;

  public String getLsScreenCaption() {
    return lsScreenCaption;
  }

  public void setLsScreenCaption(String lsScreenCaption) {
    this.lsScreenCaption = lsScreenCaption;
  }

  String lsScreenCaption;

  public List<LangModel> getScreen_Details_List() {
    return Screen_Details_List;
  }

  public void setScreen_Details_List(List<LangModel> screen_Details_List) {
    Screen_Details_List = screen_Details_List;
  }

  List<LangModel>Screen_Details_List;
}
