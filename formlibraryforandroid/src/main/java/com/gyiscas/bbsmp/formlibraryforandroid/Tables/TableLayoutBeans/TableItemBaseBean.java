package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans;

/**
 * Created by qintianhao on 15/12/11.
 */
public class TableItemBaseBean {
    private int id;
    private String name;
    private int type;
    private int subType = 0;
    private Object value;
    private boolean isLastOne = true;
    private String submitKey;
    private int isRequired;
    private int valiType;



    public TableItemBaseBean(int id, String name, int type, Object value) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public TableItemBaseBean() {
    }

    public TableItemBaseBean(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public TableItemBaseBean(int id, String name, int type, Object value, boolean isLastOne) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.isLastOne = isLastOne;
    }
    public TableItemBaseBean(int id, String name, int type, Object value, String submitKey) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.submitKey = submitKey;
    }

    public TableItemBaseBean(int id, String name, int type, int subType, Object value, boolean isLastOne) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.value = value;
        this.isLastOne = isLastOne;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public boolean isLastOne() {
        return isLastOne;
    }

    public void setIsLastOne(boolean isLastOne) {
        this.isLastOne = isLastOne;
    }

    public TableItemBaseBean(int id, String name, int type, int subType, Object value, boolean isLastOne, String submitKey) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.value = value;
        this.isLastOne = isLastOne;
        this.submitKey = submitKey;
    }

    public String getSubmitKey() {
        return submitKey;
    }

    public void setSubmitKey(String submitKey) {
        this.submitKey = submitKey;
    }

    public int getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(int isRequired) {
        this.isRequired = isRequired;
    }

    public int getValiType() {
        return valiType;
    }

    public void setValiType(int valiType) {
        this.valiType = valiType;
    }
}
