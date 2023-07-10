package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.ModelView;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

  private MemberRepository memberRepository = MemberRepository.getInstance();

  @Override
  public ModelView process(Map<String, String> paramMap) {

    return new ModelView("new-form");
  }
}
