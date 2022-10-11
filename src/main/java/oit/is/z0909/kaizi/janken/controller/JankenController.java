package oit.is.z0909.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0909.kaizi.janken.model.Janken;

@Controller
public class JankenController {
  @GetMapping("/janken")
  public String janken() {
    return "janken.html";
  }

  @PostMapping("/janken")
  public String setname(@RequestParam String name, ModelMap model) {
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/jankengame")
  public String jankengame(@RequestParam String hand, ModelMap model) {
    String cpuhand = "Gu";
    String result = "AAAAA";
    String phand = hand;
    if (phand.equals("Gu")) {
      result = "Draw";
    } else if (phand.equals("Tyoki")) {
      result = "You Lose...";
    } else if (phand.equals("Pa")) {
      result = "You Win!";
    }

    model.addAttribute("phand", phand);
    model.addAttribute("cpuhand", cpuhand);
    model.addAttribute("result", result);
    return "janken.html";
  }
}
