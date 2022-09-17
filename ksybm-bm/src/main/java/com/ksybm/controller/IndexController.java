package com.ksybm.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/****
 * 首页导航
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    private String prefix = "detector/index";

    @GetMapping("/channel-show")
    public String set()
    {
        return prefix + "/channel-show";
    }










}
