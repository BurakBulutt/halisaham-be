package com.bitirmeodev.halisahambe.domain.example;

import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import com.bitirmeodev.halisahambe.library.rest.BaseController;
import com.bitirmeodev.halisahambe.library.rest.DataResponse;
import com.bitirmeodev.halisahambe.library.rest.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("example")
public class ExampleControler extends BaseController {

    @GetMapping("{id}")
    public Response<ExampleResponse> sayHi(@PathVariable String id) {
        if (id.equals("2")){
            throw new BaseException(MessageCodes.BAD_REQUEST);
        }
        ExampleResponse exampleResponse = ExampleResponse.getInstance(id);

        return response(exampleResponse);
    }

    @GetMapping("all")
    public Response<DataResponse<ExampleResponse>> sayHiAll() {

        ExampleResponse exampleResponse2 = ExampleResponse.getInstance("ss");
        ExampleResponse exampleResponse3 = ExampleResponse.getInstance("A");
        ExampleResponse exampleResponse4 = ExampleResponse.getInstance("SSSSsss");

        List<ExampleResponse> exampleResponseList = new ArrayList<>
                (Arrays.asList(exampleResponse2, exampleResponse3, exampleResponse4));
        return response(exampleResponseList);
    }


}
