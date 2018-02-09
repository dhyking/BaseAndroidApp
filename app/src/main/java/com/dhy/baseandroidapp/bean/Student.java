package com.dhy.baseandroidapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dhy on 2018/1/23.
 */

public class Student implements Parcelable {
    private String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public Student(String mName) {
        name = mName;
    }

    protected Student(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }

    };

//    /**
//     * 参数是一个Parcel,用它来存储与传输数据
//     *
//     * @param mParcel
//     */
//    public void readFromParcel(Parcel mParcel) {
//        name = mParcel.readString();
//    }




    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
