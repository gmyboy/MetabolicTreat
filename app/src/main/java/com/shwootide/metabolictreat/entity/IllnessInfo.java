package com.shwootide.metabolictreat.entity;

/**
 * 疾病大类小类，和商品名
 * Created by GMY on 2015/10/28 16:40.
 * Contact me via email gmyboy@qq.com.
 */
public class IllnessInfo {
    /**
     * explain : null
     * dosage : 100mg*30
     * IllnessID : 1
     * maxTypeName : 糖尿病并发症药物
     * usage : null
     * MinTypeName : 抗血小板药物
     * unit : mg
     * pid1 : 2
     * pid2 : 201
     * IllnessName : 糖尿病
     * name : 拜阿斯匹林
     * id : 2000
     * pid3 : null
     */
    private String explain;
    private String dosage;
    private String IllnessID;
    private String maxTypeName;
    private String usage;
    private String MinTypeName;
    private String unit;
    private String pid1;
    private String pid2;
    private String IllnessName;
    private String name;
    private String id;
    private String pid3;

    @Override
    public String toString() {
        return name.equals("") ? super.toString() : name;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setIllnessID(String IllnessID) {
        this.IllnessID = IllnessID;
    }

    public void setMaxTypeName(String maxTypeName) {
        this.maxTypeName = maxTypeName;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setMinTypeName(String MinTypeName) {
        this.MinTypeName = MinTypeName;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPid1(String pid1) {
        this.pid1 = pid1;
    }

    public void setPid2(String pid2) {
        this.pid2 = pid2;
    }

    public void setIllnessName(String IllnessName) {
        this.IllnessName = IllnessName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPid3(String pid3) {
        this.pid3 = pid3;
    }

    public String getExplain() {
        return explain;
    }

    public String getDosage() {
        return dosage;
    }

    public String getIllnessID() {
        return IllnessID;
    }

    public String getMaxTypeName() {
        return maxTypeName;
    }

    public String getUsage() {
        return usage;
    }

    public String getMinTypeName() {
        return MinTypeName;
    }

    public String getUnit() {
        return unit;
    }

    public String getPid1() {
        return pid1;
    }

    public String getPid2() {
        return pid2;
    }

    public String getIllnessName() {
        return IllnessName;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPid3() {
        return pid3;
    }
}
