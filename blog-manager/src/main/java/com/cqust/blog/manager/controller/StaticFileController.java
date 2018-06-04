package com.cqust.blog.manager.controller;

import com.cqust.blog.common.common.ConstantCode;
import com.cqust.blog.common.entity.Advertisement;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.manager.controller.common.BaseController;
import com.cqust.blog.manager.controller.service.StaticFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/5/2.
 */
@Controller
@RequestMapping("/file")
public class StaticFileController extends BaseController {

    @Autowired private StaticFileService staticFileService;

    @RequestMapping("/img")
    public String imgUpload(@RequestParam("file") MultipartFile file, String url, String content, Integer ord) {
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        String suffix = split[split.length - 1];
        if (!suffix.matches("(png|jpeg|jpg)")) {
            return "_4FileError";
        }
        if (!url.matches("^(http://|https://)([\\w\\.]+)$")) {
            return "_4FileError";
        }
        String imgUrl = System.currentTimeMillis() + "." + suffix;
        if (!file.isEmpty()) {
            try {
                file.transferTo(new File(ConstantCode.IMG_PATH + File.separator  + imgUrl));
            } catch (IOException e) {
                e.printStackTrace();
                return "_500";
            }
        }
        Advertisement advertisement = new Advertisement();
        advertisement.setContent(content);
        advertisement.setUrl(url);
        advertisement.setImageUrl(ConstantCode.PREFFIX + imgUrl);
        advertisement.setOrd(ord);
        GeneralResult result = staticFileService.execSaveAd(advertisement);
        if(result.getCode() == 200) {
            return "adManager";
        } else {
            return "_500";
        }
    }

    /**
     * 图片广告上传
     * @return
     */
    @RequestMapping("/imgUpload")
    public String fileUpload() {
        return "imgUpload";
    }

    /**
     * 广告管理
     * @return 处理结果
     */
    @RequestMapping("/adManager")
    public String adManager() {
        return "adManager";
    }

    @RequestMapping("/words")
    public String words() {
        return "illegalWords";
    }
    /**
     * 广告列表
     * @return
     */
    @RequestMapping("/adList")
    @ResponseBody
    public GeneralResult adList() {
        return staticFileService.findAdList();
    }

    @RequestMapping("/adForbid")
    @ResponseBody
    public GeneralResult adForbid(Integer id) {
        return staticFileService.execUpdateState(id);
    }

    @RequestMapping("/adApproval")
    @ResponseBody
    public GeneralResult adApproval(Integer id) {
        return staticFileService.execadApproval(id);
    }

    @RequestMapping("addWords")
    @ResponseBody
    public GeneralResult addWords(String content) {
        return staticFileService.addWords(content);
    }

    @RequestMapping("wordsList")
    @ResponseBody
    public GeneralResult wordsList() {
        return staticFileService.findAllWords();
    }

    @RequestMapping("delWords")
    @ResponseBody
    public GeneralResult delWords(Integer id) {
        return staticFileService.delWords(id);
    }
}
