package com.example.ycq.night;

/**
 * Created by ycg on 2016-03-02.
 */
public class Tt {
    public enum Tea {
        UNKNOW("未知", 0),
        GREEN("绿茶", 1),
        RED("红茶", 2),
        BLACK("黑茶", 3);
        private int teaTypeCode;
        private String teaName;
        private Tea(String teaName, int teaTypeCode) {
            this.teaName = teaName;
            this.teaTypeCode = teaTypeCode;
        }

        public String getDescription() {
            return this.teaName;
        }

        public int getTypeCode() {
            return this.teaTypeCode;
        }

        public static Tea getTeaFromTypeCode(int teaTypeCode) {
            for(Tea tea : Tea.values()) {
                if(tea.teaTypeCode == teaTypeCode) {
                    return tea;
                }
            }
            return UNKNOW;
        }
    }

    private String title;
    private Tea tea = Tea.UNKNOW;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tea getTea() {
        return tea;
    }

    public void setTea(Tea tea) {
        android.util.Log.e("ycq", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        if(tea == null) {
            tea = Tea.UNKNOW;
            return;
        }
        this.tea = tea;
    }

    public String toString() {
        return this.title;
    }
}
