package com.ksybm.service.impl;

import java.util.List;

import com.ksybm.domain.Sms;
import com.ksybm.mapper.SmsMapper;
import com.ksybm.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ksy.bm.common.core.text.Convert;

/**
 * 短信Service业务层处理
 *
 * @author ruoyi
 * @date 2022-08-25
 */
@Service
public class SmsServiceImpl implements ISmsService
{
    @Autowired
    private SmsMapper smsMapper;

    /**
     * 查询短信
     *
     * @param ID 短信主键
     * @return 短信
     */
    @Override
    public Sms selectSmsByID(String ID)
    {
        return smsMapper.selectSmsByID(ID);
    }

    /**
     * 查询短信列表
     *
     * @param sms 短信
     * @return 短信
     */
    @Override
    public List<Sms> selectSmsList(Sms sms)
    {
        return smsMapper.selectSmsList(sms);
    }

    /**
     * 新增短信
     *
     * @param sms 短信
     * @return 结果
     */
    @Override
    public int insertSms(Sms sms)
    {
        return smsMapper.insertSms(sms);
    }

    /**
     * 修改短信
     *
     * @param sms 短信
     * @return 结果
     */
    @Override
    public int updateSms(Sms sms)
    {
        return smsMapper.updateSms(sms);
    }

    /**
     * 批量删除短信
     *
     * @param IDs 需要删除的短信主键
     * @return 结果
     */
    @Override
    public int deleteSmsByIDs(String IDs)
    {
        return smsMapper.deleteSmsByIDs(Convert.toStrArray(IDs));
    }

    /**
     * 删除短信信息
     *
     * @param ID 短信主键
     * @return 结果
     */
    @Override
    public int deleteSmsByID(String ID)
    {
        return smsMapper.deleteSmsByID(ID);
    }
}
