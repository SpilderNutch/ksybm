package com.ksybm.service;

import com.ksybm.domain.Sms;

import java.util.List;

/**
 * 短信Service接口
 *
 * @author ruoyi
 * @date 2022-08-25
 */
public interface ISmsService
{
    /**
     * 查询短信
     *
     * @param ID 短信主键
     * @return 短信
     */
    public Sms selectSmsByID(String ID);

    /**
     * 查询短信列表
     *
     * @param sms 短信
     * @return 短信集合
     */
    public List<Sms> selectSmsList(Sms sms);

    /**
     * 新增短信
     *
     * @param sms 短信
     * @return 结果
     */
    public int insertSms(Sms sms);

    /**
     * 修改短信
     *
     * @param sms 短信
     * @return 结果
     */
    public int updateSms(Sms sms);

    /**
     * 批量删除短信
     *
     * @param IDs 需要删除的短信主键集合
     * @return 结果
     */
    public int deleteSmsByIDs(String IDs);

    /**
     * 删除短信信息
     *
     * @param ID 短信主键
     * @return 结果
     */
    public int deleteSmsByID(String ID);
}
