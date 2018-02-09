package com.dhy.baseandroidapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dhy on 2018/1/19.
 */

public class Person implements Parcelable {
    private String id;
    private String name;
    private int age;

    protected Person(Parcel in) {
        id = in.readString();
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int mAge) {
        age = mAge;
    }

    public String getId() {
        return id;
    }

    public void setId(String mId) {
        id = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
