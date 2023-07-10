package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-Body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        log.info("inputStream = {}", inputStream);

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {} ", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData = {}", helloData);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-Body-json-v2")
    public String  requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody = {} ", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData = {}", helloData);

        return "ok";
    }

    // me. 얘는 안된다.. ㅠ
    @ResponseBody
    @PostMapping("/request-Body-json-v2-2")
    public String  requestBodyJsonV2_2(@ModelAttribute HelloData helloData) throws IOException {
        log.info("helloData = {}", helloData);

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-Body-json-v3")
    public String  requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {
        log.info("helloData = {}", helloData);

        return "ok";
    }

    @ResponseBody // @RequestBody 생략 불가
    @PostMapping("/request-Body-json-v3-2")
    public String  requestBodyJsonV3_2(HelloData helloData) throws IOException {
        log.info("helloData = {}", helloData);

        return "ok";
    }

    @ResponseBody // @RequestBody 생략 불가
    @PostMapping("/request-Body-json-v4")
    public String  requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException {
        HelloData helloData = httpEntity.getBody();
        log.info("helloData = {}", helloData);

        return "ok";
    }

    @ResponseBody // 나갈때도 HttpMessageConverter 적용
    @PostMapping("/request-Body-json-v5")
    public HelloData  requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {
        log.info("helloData = {}", helloData);

        return helloData;
    }

    @PostMapping("/request-Body-json-v5-2")
    public HttpEntity<HelloData>  requestBodyJsonV5_2(@RequestBody HelloData helloData) throws IOException {
        log.info("helloData = {}", helloData);

        return new HttpEntity<>(helloData);
    }
}
