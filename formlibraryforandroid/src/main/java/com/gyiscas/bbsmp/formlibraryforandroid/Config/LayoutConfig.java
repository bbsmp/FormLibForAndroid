package com.gyiscas.bbsmp.formlibraryforandroid.Config;

/**
 * Created by bbsmp on 15/12/10.
 * 表格拆分
 */
public class LayoutConfig {

    /**
     * 原子类型,spiner的选项数据，checkbox的数据等
     *
     */
    public static int CHECHBOX_ATOM_TYPE = 9;//复选框
    public static int SPINNERITEM_ATOM_TYPE = 10;//pinner下拉项

    /**
     **基本类型
     **/
    public static int EDITTEXT_TYPE = 1;//基本编辑框
    public static int AUTOCOM_EDITTEXT_TYPE = 11;//自动补全编辑框
    public static int TEXT_LABLE_TYPE = 2;//基本文本
    public static int SPINNER_TYPE = 3;//基本下拉列表
    public static int SWITCH_TYPE = 4;//基本开关选择器（是｜｜否）
    public static int DATE_PICKER_TYPE = 5;//基本时间选择器（用于填时间的地方）
    public static int SPINER_LINKAGE_TYPE = 6;//联动下拉列表
    public static int CHECKBOX_TYPE = 7;//基本复选框
    public static int LOCATION_TYPE = 8;//位置类型（需要定位）


    /**
     *	可扩展组
     **/
    public static int ACTION_EXPENDABLE = 100;//可动扩展组
    public static int ACTION_EXPENDABLE_ITEM = 111;//可动扩展组

//    /**
//     *	复合类型
//     **/
//    public static int EDITTEXT_TYPE_GROUP = 9;//基本编辑框组
//    public static int SWITCH_TYPE_GROUP = 10;//基本开关选择器组
//    public static int SPINNER_TYPE_GROUP = 11;//基本下来列表组
//    public static int DATE_PICKER_TYPE_GROUP = 12;//基本时间选择器组
//    public static int SPINER_LINKAGE_TYPE_GROUP = 13;//基本联动下来列表组
//    public static int TEXT_LABLE_TYPE_GROUP= 14;//基本文本标签组
//    public static int CHECKBOX_TYPE_GROUP= 15;//基本复选框组

    /**
     *	可扩展组
     **/
//    public static int ACTION_EXPENDABLE = 100;//可动扩展组


    /**
     * 自定义类型
     */


    /**
     * 表单验证类型
     */
    public static int NOT_REQUIRED = 0;//非必填
    public static int REQUIRED = 1;//必填
    public static int NUMBER = 2;//数字
    public static int LETTER_AND_NUM = 3;//数字和字符
    public static int CHINESE = 4;//中文
    public static int LETTER = 5;//字母
    public static int NOT_SURE = 6;//不确定
    public static int PHONE = 7;//电话
    public static int MOBILE = 8;//手机
    public static int YOUCODE = 9;//邮编



}
