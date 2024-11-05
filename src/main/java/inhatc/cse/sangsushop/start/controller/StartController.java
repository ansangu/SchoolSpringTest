package inhatc.cse.sangsushop.start.controller;

import inhatc.cse.sangsushop.start.dto.StartDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    
    
    @GetMapping("/") //들어오는 주소
    public String start(Model model){   //매개변수로 model을 추가하여 웹페이지로 해당 데이터를 전달가는
        StartDto startDto = StartDto.builder()
                .dept("컴시과")
                .grade(3)
                .name("홍길동")
                .build();

        model.addAttribute("data",startDto);    //모델의 상세설정
        //return "start"; //start.html로 이동
        return "temp/temp";
    }
    
}
