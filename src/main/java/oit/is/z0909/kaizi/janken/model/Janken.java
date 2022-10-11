package oit.is.z0909.kaizi.janken.model;

public class Janken {
  public String name;
  public String phand;

  public Janken(String phand) {
    this.phand = phand;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhand(String hand) {
    this.phand = hand;
  }

  public String getPhand() {
    return phand;
  }

}
