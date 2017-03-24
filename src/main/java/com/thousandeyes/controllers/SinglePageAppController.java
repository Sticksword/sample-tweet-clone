package com.thousandeyes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Michael on 3/24/2017.
 */
@Controller
public class SinglePageAppController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index.html";
    }
    // in the essence of time, I am opting to do bonus bullet 2 instead of 3
}
