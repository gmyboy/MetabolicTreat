package com.shwootide.metabolictreat.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.afollestad.materialdialogs.internal.MDRootLayout;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateChangedListener;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.entity.Diagnosis;
import com.shwootide.metabolictreat.entity.Diagnosis_Submit;
import com.shwootide.metabolictreat.entity.FollowSelect;
import com.shwootide.metabolictreat.entity.PicInfo;
import com.shwootide.metabolictreat.entity.Sheet;
import com.shwootide.metabolictreat.entity.Sheet_Submit;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 * Created by GMY on 2015/8/25 10:08.
 * Contact me via email gmyboy@qq.com.
 */
public class CommonUtil {
    /**
     * 判断集合是否为空
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(List<Object> o) {
        if (o == null) return true;
        if (o.size() == 0) return true;
        return false;
    }

    public static boolean isEmptys(List<PicInfo> o) {
        if (o == null) return true;
        if (o.size() == 0) return true;
        return false;
    }

    /**
     * 显示Toast信息
     *
     * @param context 调用者
     * @param msg     信息内容
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 用过身高体重获得BMI值
     *
     * @param str_tz kg
     * @param str_sg cm
     * @return
     */
    public static int getBMI(String str_tz, String str_sg) {
        double tz = Double.parseDouble(str_tz);
        double sg = Double.parseDouble(str_sg);
        double d = Math.pow(sg / 100, 2);
        return (int) (tz / d);
    }

    /**
     * 获得腰臀比
     *
     * @param str_tw cm
     * @param str_yw cm
     * @return
     */
    public static String getYTB(String str_tw, String str_yw) {
        DecimalFormat df = new DecimalFormat("0.00");
        double tw = Double.parseDouble(str_tw);
        double yw = Double.parseDouble(str_yw);
        return df.format(yw / tw);
    }

    /**
     * 获得LH/FSH
     *
     * @param str_lh  IU/L
     * @param str_fsh IU/L
     * @return
     */
    public static String getLHFSH(String str_lh, String str_fsh) {
        DecimalFormat df = new DecimalFormat("0.00");
        double lh = Double.parseDouble(str_lh);
        double fsh = Double.parseDouble(str_fsh);
        return df.format(lh / fsh);
    }

    /**
     * 格式化医院编号   0001
     *
     * @param format eg.  "00"
     */
    public static String formatInt(int i, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(i);
    }

    /**
     * 获取yyyy-MM-ddTHH:mm:ss格式的系统日期
     *
     * @return
     */
    public static String getSysDate() {
        /* 默认取服务器日期 */
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = simpledateformat.format(Calendar.getInstance().getTime());
        return s;
    }

    /**
     * 获取yyyy年MM月dd日格式的系统日期
     *
     * @return
     */
    public static String getSysDate2() {
        /* 默认取服务器日期 */
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy年MM月dd日");
        String s = simpledateformat.format(Calendar.getInstance().getTime());
        return s;
    }

    /**
     * 获取yyyy-MM-dd格式的系统日期
     *
     * @return
     */
    public static String getSysDate3() {
        /* 默认取服务器日期 */
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        String s = simpledateformat.format(Calendar.getInstance().getTime());
        return s;
    }

    /**
     * 获取系统年份
     *
     * @return
     */
    public static String getSysYear() {
        return getSysDate().split("-")[0];
    }

    /**
     * 把指定日期字符串转换成calendar格式
     *
     * @param str yyyy年MM月dd日
     * @return
     */
    public static Calendar parseFromStr(String str) {
        Calendar dayc1 = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        Date daystart = null;
        try {
            daystart = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dayc1.setTime(daystart);
        return dayc1;
    }

    /**
     * 把指定日期字符串转换成calendar格式
     *
     * @param str yyyy-MM-dd
     * @return
     */
    public static Calendar parseFromStr(String str, String format) {
        Calendar dayc1 = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(format);
        Date daystart = null;
        try {
            daystart = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dayc1.setTime(daystart);
        return dayc1;
    }

    /**
     * 把 时间格式[2015-09-18T09:12:34]转换成时间格式[2015年09月18日 09:12:34]
     *
     * @param str 2015-09-18T09:12:34
     * @return
     */
    public static String parseStr(String str) {
        return str.substring(0, 4) + "年" + str.substring(5, 7)
                + "月" + str.substring(8, 10) + "日 " + str.substring(11);
    }

    /**
     * 把 时间格式[2015-09-18T09:12:34]转换成时间格式[2015年09月18日 09:12:34]
     *
     * @param str 2015-09-18T09:12:34
     * @return
     */
    public static String parseShortStr(String str) {
        return str.substring(0, 4) + "-" + str.substring(5, 7)
                + "-" + str.substring(8, 10) + " " + str.substring(11, 16);
    }

    /**
     * 把 时间格式[2015-09-18T09:12:34]转换成时间格式[2015年09月18日]
     *
     * @param str 2015-09-18T09:12:34
     * @return
     */
    public static String parseForminnerStr(String str) {
        return str.substring(0, 4) + "年" + str.substring(5, 7)
                + "月" + str.substring(8, 10) + "日 ";
    }

    /**
     * 把 时间格式[2015-09-18T09:12:34]转换成时间格式[2015-09-18]
     *
     * @param str 2015-09-18T09:12:34
     * @return
     */
    public static String parseDateStr(String str) {
        return str.substring(0, 10);
    }

    /**
     * 生成GUID，用户提交数据
     *
     * @return
     */
    public static String generateGUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 拆分字符串
     *
     * @param str 待拆分的字符串
     */
    public static List<String> splitStr(String str) {
        return Arrays.asList(str.split("/"));
    }

    /**
     * 替换制表符
     *
     * @param str 待替换的字符串
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * @param targetList 目标排序List
     * @param sortField  排序字段
     * @param sortMode   排序方式 desc 降序
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> void sortList(List<T> targetList, final String sortField, final String sortMode) {
        Collections.sort(targetList, new Comparator() {
            public int compare(Object obj1, Object obj2) {
                int retVal = 0;
                try {
                    Method method1 = ((T) obj1).getClass().getMethod(sortField, new Class[0]);
                    Method method2 = ((T) obj2).getClass().getMethod(sortField, new Class[0]);
                    if (sortMode != null && Config.SORT_DESC.equals(sortMode)) {
                        retVal = method2.invoke(((T) obj2), new Object[]{}).toString().compareTo(method1.invoke(((T) obj1), new Object[]{}).toString()); // 倒序
                    } else {
                        retVal = method1.invoke(((T) obj1), new Object[]{}).toString().compareTo(method2.invoke(((T) obj2), new Object[]{}).toString()); // 正序
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return retVal;
            }
        });
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInputView(Activity activity, View view) {
        InputMethodManager manager = ((InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE));
        manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
//            if (activity.getCurrentFocus() != null)
//
//        }
    }

    /**
     * 显示软键盘
     */
    public static void showSoftInputView(Activity activity, View view) {
        InputMethodManager manager = ((InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (activity.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    public static int parserInt(String fields1, int i) {
        return Integer.parseInt(fields1.split("-")[i]);
    }

    public static int parserInt(String fields1) {
        return Integer.parseInt(fields1);
    }

//    public static Diagnosis fromDiagnosis_Submit(Diagnosis_Submit submit) {
//        Diagnosis diagnosis = null;
//        if (submit != null) {
//            diagnosis = new Diagnosis();
//            diagnosis.setName(submit.getName());
//
//        }
//        return diagnosis;
//    }

    /**
     * diagnosis转成对应的Diagnosis_Submit
     *
     * @param diagnosis
     * @return
     */
    public static Diagnosis_Submit fromDiagnosis(Diagnosis diagnosis) {
        Diagnosis_Submit submit = null;
        if (diagnosis != null) {
            submit = new Diagnosis_Submit();
            submit.setMaxTypeName(diagnosis.getType1Name());
            submit.setMaxTypeid(diagnosis.getType1id());
            submit.setMinTypeid(diagnosis.getId());
            submit.setMinTypeName(diagnosis.getName());
            submit.setMHTypeID(diagnosis.getId());
        }
        return submit;
    }

    /**
     * Sheet转成对应的Sheet_Submit
     *
     * @param sheet
     * @return
     */
    public static Sheet_Submit fromSheet(Sheet sheet) {
        Sheet_Submit submit = null;
        if (sheet != null) {
            submit = new Sheet_Submit();
            submit.setNametype1(sheet.getTypeID1());
            submit.setNametype2(sheet.getTypeID2());
            submit.setNametype3(sheet.getTypeID3());
            submit.setCheckValues(sheet.getNameType3());//小名称存于nametype3
//            submit.setType1(sheet.getTypeID1());
//            submit.setType2(sheet.getTypeID2());
//            submit.setType3(sheet.getTypeID3());
        }
        return submit;
    }

    /**
     * 控制小数点位数
     *
     * @return
     */
    public static InputFilter getFilter() {
        InputFilter lengthfilter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // 删除等特殊字符，直接返回
                if ("".equals(source.toString())) {
                    return null;
                }
                String dValue = dest.toString();
                String[] splitArray = dValue.split("//.");
                if (splitArray.length > 1) {
                    String dotValue = splitArray[1];
                    int diff = dotValue.length() + 1 - Config.DECIMAL_DIGITS;
                    if (diff > 0) {
                        return source.subSequence(start, end - diff);
                    }
                }
                return null;
            }
        };
        return lengthfilter;
    }

    /**
     * 控制小数点位数
     *
     * @return
     */
    public static TextWatcher getTextWatch() {

        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s == null ? null : s.toString();
                if (s == null || s.length() == 0) {
                    return;
                }
                int size = content.length();
                if (content.contains(".")) { //判断之前有没有输入过点
                    if (content.substring(content.lastIndexOf("."), content.length() - 1).length() > Config.DECIMAL_DIGITS) {
                        s.delete(size - 1, size);//之前有输入过点，删除重复输入的点}
                    }
                }
            }
        };
    }

    public static List<FollowSelect> generatDate(String illnessId) {
        List<FollowSelect> datas = new ArrayList<>();
        FollowSelect item;
        switch (illnessId) {
            case "1": {
                item = new FollowSelect();
                item.setName("FBG");
                item.setDos("mmol/L");
                item.setType("4");
                item.setTitle("Fields1");
                datas.add(item);
                item = new FollowSelect();
                item.setName("2hPG");
                item.setDos("mmol/L");
                item.setType("4");
                item.setTitle("Fields2");
                datas.add(item);
                item = new FollowSelect();
                item.setName("HbA1c");
                item.setDos("%");
                item.setType("4");
                item.setTitle("Fields3");
                datas.add(item);
                item = new FollowSelect();
                item.setName("血压");
                item.setDos("mmHg");
                item.setType("1");
                item.setTitle("Fields1");
                datas.add(item);
                item = new FollowSelect();
                item.setName("体重");
                item.setDos("Kg");
                item.setType("1");
                item.setTitle("Fields5");
                datas.add(item);
                item = new FollowSelect();
                item.setName("腰围");
                item.setDos("cm");
                item.setType("1");
                item.setTitle("Fields7");
                datas.add(item);
//                item = new FollowSelect();
//                item.setName("TC");
//                item.setDos("mmol/L");
//                item.setType("4");
//                item.setTitle("Fields");
//                datas.add(item);
//                item = new FollowSelect();
//                item.setName("TG");
//                item.setDos("mmol/L");
//                item.setType("4");
//                item.setTitle("Fields");
//                datas.add(item);
                item = new FollowSelect();
                item.setName("LDL-C");
                item.setDos("mmol/L");
                item.setType("2");
                item.setTitle("Fields5");
                datas.add(item);
                item = new FollowSelect();
                item.setName("血尿酸");
                item.setDos("umol/L");
                item.setType("2");
                item.setTitle("Fields10");
                datas.add(item);
                item = new FollowSelect();
                item.setName("ALT");
                item.setDos("U/L");
                item.setType("2");
                item.setTitle("Fields7");
                datas.add(item);
                item = new FollowSelect();
                item.setName("AST");
                item.setDos("U/L");
                item.setType("2");
                item.setTitle("Fields8");
                datas.add(item);
                break;
            }
            case "2": {
                item = new FollowSelect();
                item.setName("FT3");
                item.setDos("Pmol/L");
                item.setType("5");
                item.setTitle("Fields1");
                datas.add(item);
                item = new FollowSelect();
                item.setName("FT4");
                item.setDos("Pmol/L");
                item.setType("5");
                item.setTitle("Fields2");
                datas.add(item);
                item = new FollowSelect();
                item.setName("TSH");
                item.setDos("MIU/L");
                item.setType("5");
                item.setTitle("Fields3");
                datas.add(item);
                item = new FollowSelect();
                item.setName("TPOAb");
                item.setDos("IU/ML");
                item.setType("5");
                item.setTitle("Fields4");
                datas.add(item);
                item = new FollowSelect();
                item.setName("TGAb");
                item.setDos("IU/ML");
                item.setType("5");
                item.setTitle("Fields5");
                datas.add(item);
                item = new FollowSelect();
                item.setName("TRAb");
                item.setDos("IU/L");
                item.setType("5");
                item.setTitle("Fields6");
                datas.add(item);
                item = new FollowSelect();
                item.setName("左结节最大");
                item.setDos("mm");
                item.setType("3");
                item.setTitle("Fields4");
                datas.add(item);
                item = new FollowSelect();
                item.setName("右结节最大");
                item.setDos("mm");
                item.setType("3");
                item.setTitle("Fields6");
                datas.add(item);
                item = new FollowSelect();
                item.setName("ALP");
                item.setDos("U/L");
                item.setType("2");
                item.setTitle("Fields10");
                datas.add(item);
                item = new FollowSelect();
                item.setName("ASP");
                item.setDos("U/L");
                item.setType("2");
                item.setTitle("Fields11");
                datas.add(item);
                item = new FollowSelect();
                item.setName("WBC");
                item.setDos("×10^9/L");
                item.setType("2");
                item.setTitle("Fields7");
                datas.add(item);
                item = new FollowSelect();
                item.setName("中性细胞%");
                item.setDos("%");
                item.setType("2");
                item.setTitle("Fields8");
                datas.add(item);
                item = new FollowSelect();
                item.setName("中性细胞绝对值");
                item.setDos("×10^9/L");
                item.setType("2");
                item.setTitle("Fields9");
                datas.add(item);
                break;
            }
            case "3": {
                item = new FollowSelect();
                item.setName("T");
                item.setDos("nmol/L");
                item.setType("2");
                item.setTitle("Fields13");
                datas.add(item);
                item = new FollowSelect();
                item.setName("SHBG");
                item.setDos("nmol/L");
                item.setType("2");
                item.setTitle("Fields14");
                datas.add(item);
//                item = new FollowSelect();
//                item.setName("血脂");
//                item.setDos("U/L");
//                item.setType("2");
//                item.setTitle("Fields8");
//                datas.add(item);
                item = new FollowSelect();
                item.setName("血尿酸");
                item.setDos("umol/L");
                item.setType("2");
                item.setTitle("Fields7");
                datas.add(item);
                item = new FollowSelect();
                item.setName("血压");
                item.setDos("mmHg");
                item.setType("1");
                item.setTitle("Fields1");
                datas.add(item);
                item = new FollowSelect();
                item.setName("体重");
                item.setDos("Kg");
                item.setType("1");
                item.setTitle("Fields5");
                datas.add(item);
                item = new FollowSelect();
                item.setName("腰围");
                item.setDos("cm");
                item.setType("1");
                item.setTitle("Fields7");
                datas.add(item);
                item = new FollowSelect();
                item.setName("ALT");
                item.setDos("U/L");
                item.setType("2");
                item.setTitle("Fields4");
                datas.add(item);
                item = new FollowSelect();
                item.setName("AST");
                item.setDos("U/L");
                item.setType("2");
                item.setTitle("Fields5");
                datas.add(item);
                break;
            }
            case "9000": {
//                item = new FollowSelect();
//                item.setName("血脂");
//                item.setDos("U/L");
//                item.setType("2");
//                item.setTitle("Fields4");
//                datas.add(item);
                item = new FollowSelect();
                item.setName("血尿酸");
                item.setDos("U/L");
                item.setType("2");
                item.setTitle("Fields10");
                datas.add(item);
                item = new FollowSelect();
                item.setName("血压");
                item.setDos("mmHg");
                item.setType("1");
                item.setTitle("Fields1");
                datas.add(item);
                item = new FollowSelect();
                item.setName("体重");
                item.setDos("Kg");
                item.setType("1");
                item.setTitle("Fields5");
                datas.add(item);
                item = new FollowSelect();
                item.setName("腰围");
                item.setDos("cm");
                item.setType("1");
                item.setTitle("Fields7");
                datas.add(item);

                break;
            }
        }
        return datas;
    }

    /**
     * 通过字段名称获取该字段所对应的值
     *
     * @param obj
     * @param fieldName
     */
    public static String reflect(Object obj, String fieldName) {
        String back = "";
        if (obj == null)
            return back;
        Field[] fields = obj.getClass().getDeclaredFields();
        String[] types1 = {"int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte"};
        String[] types2 = {"Integer", "java.lang.String", "java.lang.Boolean", "java.lang.Character", "java.lang.Float", "java.lang.Double", "java.lang.Long", "java.lang.Short", "java.lang.Byte"};
        for (int j = 0; j < fields.length; j++) {
            fields[j].setAccessible(true);
            // 字段名
            if (fields[j].getName().equals(fieldName)) {
                // 字段值
                for (int i = 0; i < types1.length; i++) {
                    if (fields[j].getType().getName().equalsIgnoreCase(types1[i]) || fields[j].getType().getName().equalsIgnoreCase(types2[i])) {
                        try {
                            back = String.valueOf(fields[j].get(obj));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
        }
        return back;
    }

    /**
     * 显示单个编辑框的dialog
     *
     * @param activity
     * @param view
     * @param defaultValue
     */
    public static void showEditDialog(final Activity activity, final View view, String defaultValue) {
        View rootView = activity.getLayoutInflater().inflate(R.layout.dialog_edit, null);
        final EditText et = (EditText) rootView.findViewById(R.id.et_dialog_family);
        if (view instanceof TextView) {
            if (!TextUtils.isEmpty(defaultValue)) {
                et.setText(defaultValue);
                et.setSelection(defaultValue.length());
            }
        }
        MaterialDialog dialog = new MaterialDialog.Builder(activity)
                .customView(rootView, false)
                .autoDismiss(false)
                .cancelable(false)
                .positiveText("确定")
                .negativeText("取消")
                .neutralText("清空")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (view instanceof TextView) {
                            ((TextView) view).setText(et.getText());
                            hideSoftInputView(activity, et);
                            dialog.dismiss();
                        }
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        et.setText("");
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        hideSoftInputView(activity, et);//一定要在dismiss之前
                        dialog.dismiss();
                    }
                })
                .build();
        dialog.show();
        resetDialogParam(activity, dialog);
    }

    /**
     * 显示日期选择框
     *
     * @param activity
     * @param view
     */
    public static void showDateDialog(final Activity activity, final View view) {
        Calendar c;
        if (!(view instanceof TextView)) {
            return;
        }
        String defaultDate = ((TextView) view).getText().toString();
        if (TextUtils.isEmpty(defaultDate) || defaultDate.contains("点击添加日期")) {//排除提醒页面按钮形式的时间选择框
            c = Calendar.getInstance();
        } else {
            c = parseFromStr(defaultDate, "yyyy年MM月dd日");
        }
        DatePickerDialog dialog = new DatePickerDialog(activity,
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker mView, int year, int monthOfYear, int dayOfMonth) {
                        if (view instanceof TextView) {
                            ((TextView) view).setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                        }
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
        resetDialogParam(activity, dialog);
    }

    static int mMonthOfYear = 12;
    static int mYear = 2015;
    static int mDayOfMonth = 12;

    /**
     * 显示日期选择框
     *
     * @param activity
     * @param view
     */
    public static void showFormatDateDialog(final Activity activity, final View view) {
        final Calendar c;
        if (!(view instanceof Button)) {
            return;
        }
        String defaultDate = ((Button) view).getText().toString();
        if (TextUtils.isEmpty(defaultDate) || defaultDate.contains("时间")) {//排除提醒页面按钮形式的时间选择框
            c = Calendar.getInstance();
        } else {
            c = parseFromStr(defaultDate, "yyyy-MM-dd HH:mm");
        }
        DatePickerDialog dateDialog = new DatePickerDialog(activity,
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker dateView, final int year, final int monthOfYear, final int dayOfMonth) {
                        mYear = year;
                        mMonthOfYear = monthOfYear;
                        mDayOfMonth = dayOfMonth;
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dateDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TimePickerDialog timeDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timeView, int hourOfDay, int minute) {
                        if (view instanceof Button) {
                            ((Button) view).setText(mYear + "-" + (mMonthOfYear + 1) + "-" + mDayOfMonth + " " + hourOfDay + ":" + minute);
                        }
                    }
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);
                timeDialog.show();
            }
        });
        dateDialog.show();
        resetDialogParam(activity, dateDialog);
    }

    /**
     * 显示日期空间
     *
     * @param activity
     * @param view
     */
    public static void showMaterialCalendarDialog(final Activity activity, final View view) {
        final MaterialDialog dialog;
        if (!(view instanceof TextView)) {
            return;
        }
        View v = LayoutInflater.from(activity).inflate(R.layout.dialog_calendar, null);
        MaterialCalendarView mcv = (MaterialCalendarView) v.findViewById(R.id.calendarView);
        dialog = new MaterialDialog.Builder(activity).customView(v, false).build();
        if (((TextView) view).getText().equals(""))
            mcv.setSelectedDate(Calendar.getInstance());
        else
            mcv.setSelectedDate(CommonUtil.parseFromStr(((TextView) view).getText().toString()));
        mcv.setOnDateChangedListener(new OnDateChangedListener() {
            @Override
            public void onDateChanged(MaterialCalendarView materialCalendarView, @Nullable CalendarDay calendarDay) {
                ((TextView) view).setText(calendarDay.getYear() + "年" + CommonUtil.formatInt((calendarDay.getMonth() + 1), "00") + "月" + calendarDay.getDay() + "日");
                dialog.dismiss();
            }
        });
        dialog.show();
        //关闭自动弹出输入法
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.55);    //宽度设置为屏幕的0.7
        dialog.getWindow().setAttributes(p);     //设置生效
    }

    /**
     * 显示一个数字选择框
     *
     * @param activity
     * @param view
     */
    public static void showNumberPickerDialog(final Activity activity, final View view) {
        if (!(view instanceof TextView)) {
            return;
        }
        View rootView = activity.getLayoutInflater().inflate(R.layout.dialog_number_five, null);
        final NumberPicker np1 = (NumberPicker) rootView.findViewById(R.id.np_dialog_one1);
        final NumberPicker np2 = (NumberPicker) rootView.findViewById(R.id.np_dialog_one2);
        final NumberPicker np3 = (NumberPicker) rootView.findViewById(R.id.np_dialog_one3);
        final NumberPicker np4 = (NumberPicker) rootView.findViewById(R.id.np_dialog_one4);
        final NumberPicker np5 = (NumberPicker) rootView.findViewById(R.id.np_dialog_one5);
        np2.setWrapSelectorWheel(true);
        np1.setMaxValue(5);
        np1.setMinValue(0);
        np2.setMaxValue(9);
        np2.setMinValue(0);
        np3.setMaxValue(9);
        np3.setMinValue(0);
        np4.setMaxValue(9);
        np4.setMinValue(0);
        np5.setMaxValue(9);
        np5.setMinValue(0);
        final double defaultValues = TextUtils.isEmpty(((TextView) view).getText()) ? 000.00 : Double.parseDouble(((TextView) view).getText().toString());
        np1.setValue((int) (defaultValues / 100));
        np2.setValue((int) (defaultValues * 100 % 10000 / 1000));
        np3.setValue((int) (defaultValues * 100 % 1000 / 100));
        np4.setValue((int) (defaultValues * 100 % 100 / 10));
        np5.setValue((int) (defaultValues * 100 % 10));
        MaterialDialog dialog = new MaterialDialog.Builder(activity)
                .customView(rootView, false)
                .autoDismiss(false)
                .cancelable(false)
                .positiveText("确定")
                .negativeText("取消")
                .neutralText("重置")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        String aValue, bValue;
                        if (view instanceof TextView) {
                            //格式化小数点之前的数
                            if (np1.getValue() == 0) {
                                if (np2.getValue() == 0) {
                                    aValue = String.valueOf(np3.getValue());
                                } else {
                                    aValue = String.valueOf(np2.getValue()) + String.valueOf(np3.getValue());
                                }
                            } else {
                                aValue = String.valueOf(np1.getValue()) + String.valueOf(np2.getValue()) + String.valueOf(np3.getValue());
                            }
                            if (np5.getValue() == 0) {
                                if (np4.getValue() != 0) {
                                    bValue = "." + String.valueOf(np4.getValue());
                                } else {
                                    bValue = "";
                                }
                            } else {
                                bValue = "." + String.valueOf(np4.getValue()) + String.valueOf(np5.getValue());
                            }

                            ((TextView) view).setText(aValue + bValue);
                            hideSoftInputView(activity, np1);
                            hideSoftInputView(activity, np2);
                            hideSoftInputView(activity, np3);
                            hideSoftInputView(activity, np4);
                            hideSoftInputView(activity, np5);
                            dialog.dismiss();
                        }
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        np1.setValue(0);
                        np2.setValue(0);
                        np3.setValue(0);
                        np4.setValue(0);
                        np5.setValue(0);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        hideSoftInputView(activity, np1);
                        hideSoftInputView(activity, np2);
                        hideSoftInputView(activity, np3);
                        hideSoftInputView(activity, np4);
                        hideSoftInputView(activity, np5);
                        dialog.dismiss();
                    }
                })
                .build();
        dialog.show();
        resetDialogParam(activity, dialog);
    }

    public static void resetDialogParam(Activity activity, Dialog dialog) {
        //关闭自动弹出输入法
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
//        p.height = (int) (d.getHeight() * 0.8); //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 0.7);    //宽度设置为屏幕的0.7
        dialog.getWindow().setAttributes(p);     //设置生效
    }

    /**
     * 查看一个字符串是否可以转换为数字
     *
     * @param str 字符串
     * @return true 可以; false 不可以
     */
    public static boolean isStr2Num(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
