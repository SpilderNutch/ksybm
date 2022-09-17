package com.ksybm.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ksy.bm.common.utils.security.Md5Utils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ksy.bm.common.annotation.Excel;
import com.ksy.bm.common.core.domain.BaseEntity;

/**
 * 短信对象 sms
 *
 * @author ruoyi
 * @date 2022-08-25
 */
public class Sms extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String ID;

    /** 发送者号码 */
    @Excel(name = "发送者号码")
    private String ADDRESS;

    /** 发送者时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送者时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    /** 发送内容 */
    @Excel(name = "发送内容")
    private String BODY;

    /** 发送类型（1：接收，2发送） */
    @Excel(name = "发送类型", readConverterExp = "1=：接收，2发送")
    private String sendType;

    /** 是否处理 */
    @Excel(name = "是否处理")
    private String dealFlag;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 处理者 */
    @Excel(name = "处理者")
    private String dealBy;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dealTime;

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getID()
    {
        return ID;
    }
    public void setADDRESS(String ADDRESS)
    {
        this.ADDRESS = ADDRESS;
    }

    public String getADDRESS()
    {
        return ADDRESS;
    }
    public void setSendTime(Date sendTime)
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime()
    {
        return sendTime;
    }
    public void setBODY(String BODY)
    {
        this.BODY = BODY;
    }

    public String getBODY()
    {
        return BODY;
    }
    public void setSendType(String sendType)
    {
        this.sendType = sendType;
    }

    public String getSendType()
    {
        return sendType;
    }
    public void setDealFlag(String dealFlag)
    {
        this.dealFlag = dealFlag;
    }

    public String getDealFlag()
    {
        return dealFlag;
    }
    public void setCreatedBy(String createdBy)
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
    public void setDealBy(String dealBy)
    {
        this.dealBy = dealBy;
    }

    public String getDealBy()
    {
        return dealBy;
    }
    public void setDealTime(Date dealTime)
    {
        this.dealTime = dealTime;
    }

    public Date getDealTime()
    {
        return dealTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ID", getID())
            .append("ADDRESS", getADDRESS())
            .append("sendTime", getSendTime())
            .append("BODY", getBODY())
            .append("sendType", getSendType())
            .append("dealFlag", getDealFlag())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("dealBy", getDealBy())
            .append("dealTime", getDealTime())
            .toString();
    }

}
