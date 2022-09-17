package com.ksybm.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.ksy.bm.common.annotation.Log;
import com.ksy.bm.common.core.controller.BaseController;
import com.ksy.bm.common.core.domain.AjaxResult;
import com.ksy.bm.common.core.domain.entity.SysDictData;
import com.ksy.bm.common.core.page.TableDataInfo;
import com.ksy.bm.common.enums.BusinessType;
import com.ksy.bm.common.utils.poi.ExcelUtil;
import com.ksy.bm.common.utils.security.Md5Utils;
import com.ksybm.domain.Sms;
import com.ksybm.service.ISmsService;
import com.ksy.bm.system.service.ISysDictDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 短信Controller
 *
 * @author ruoyi
 * @date 2022-08-25
 */
@Controller
@RequestMapping("/sms")
public class SmsController extends BaseController
{
    private String prefix = "sms";

    public static Set<String> FILTER_WORDS = Sets.newHashSet ();

    public static Logger LOGGER = LoggerFactory.getLogger (SmsController.class);

    @Autowired
    private ISmsService smsService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @RequiresPermissions("sms:view")
    @GetMapping()
    public String sms()
    {
        LOGGER.info (prefix+"/sms");
        return prefix +"/sms" ;
    }

    /**
     * 查询短信列表
     */
    @RequiresPermissions("sms:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Sms sms)
    {
        startPage();
        List<Sms> list = smsService.selectSmsList(sms);
        return getDataTable(list);
    }

    /**
     * 导出短信列表
     */
    @RequiresPermissions("sms:export")
    @Log(title = "短信", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Sms sms)
    {
        List<Sms> list = smsService.selectSmsList(sms);
        ExcelUtil<Sms> util = new ExcelUtil<Sms>(Sms.class);
        return util.exportExcel(list, "短信数据");
    }


    @PostMapping("/batchSave")
    @ResponseBody
    public AjaxResult batchSave(String smsData)
    {
        if(!Strings.isNullOrEmpty (smsData)){
            List<Sms> smsList = JSON.parseArray (smsData,Sms.class);
            if(smsList!=null && smsList.size ()>0){
                for (int i=0; i<smsList.size (); i++){
                    Sms sms = smsList.get (i);
                    StringBuilder builder = new StringBuilder ();
                    builder.append (sms.getADDRESS ());
                    builder.append (sms.getBODY ());
                    builder.append (new DateTime (sms.getSendTime ()).toString ("yyyy-MM-dd hh:mm:ss"));
                    builder.append (sms.getSendType ());
                    String id = Md5Utils.hash (builder.toString ());

                    System.out.println (id);
                    sms.setID (id);
                    //sms.setID (IdUtils.randomUUID ());
                    sms.setCreatedTime (new Date ());
                    sms.setDealFlag ("0");
                    boolean isContains = false;
                    //当过滤词更新或者过滤词未加载
                    if(FILTER_WORDS.size ()==0){
                        SysDictData sysDictData = new SysDictData ();
                        sysDictData.setDictType ("sys_bmjd_glc");
                        List<SysDictData> sysDictDataList =  sysDictDataService.selectDictDataList (sysDictData);
                        //forEach并没有返回值
                        sysDictDataList.forEach ( (e) -> {
                            FILTER_WORDS.add (e.getDictLabel ());
                        });
                    }

                    for(String word : FILTER_WORDS){
                        if(sms.getBODY ().contains (word)){
                            isContains = true;
                            break;
                        }
                    }

                    if(smsService.selectSmsByID (id)==null){
                        if(isContains){
                            LOGGER.info ("Insert 成功！"+sms.toString ());
                            smsService.insertSms (sms);
                        }
                        LOGGER.info ("SMS RECEIVED!"+sms);
                    }
                }
            }
        }
        return AjaxResult.success ("成功！");
    }



    /**
     * 新增短信
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存短信
     */
    @RequiresPermissions("sms:add")
    @Log(title = "短信", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Sms sms)
    {
        return toAjax(smsService.insertSms(sms));
    }

    /**
     * 修改短信
     */
    @RequiresPermissions("sms:edit")
    @GetMapping("/edit/{ID}")
    public String edit(@PathVariable("ID") String ID, ModelMap mmap)
    {
        Sms sms = smsService.selectSmsByID(ID);
        mmap.put("sms", sms);
        return prefix + "/edit";
    }

    /**
     * 修改保存短信
     */
    @RequiresPermissions("sms:edit")
    @Log(title = "短信", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Sms sms)
    {
        return toAjax(smsService.updateSms(sms));
    }

    /**
     * 删除短信
     */
    @RequiresPermissions("sms:remove")
    @Log(title = "短信", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(smsService.deleteSmsByIDs(ids));
    }



    @GetMapping( "/refreshFilterWords")
    @ResponseBody
    public AjaxResult refreshFilterWords()
    {
        FILTER_WORDS = Sets.newHashSet ();
        return AjaxResult.success ("刷新白马过滤词成功");
    }


}
