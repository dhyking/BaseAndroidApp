package com.dhy.baseandroidapp;


import com.dhy.baseandroidapp.bean.Student;

/**
非基本类型的数据需要导入，比如上面的 Student，需要导入它的全路径。
这里的 Student 指的是 Student.aidl，然后通过 Student.aidl 又找到真正的实体 Student 类。
方法参数中，除了基本数据类型，其他类型的参数都需要标上方向类型
in(输入), out(输出), inout(输入输出)
*/

interface IMyAidl{
        /**
         * 除了基本数据类型，其他类型的参数都需要标上方向类型：in(输入), out(输出), inout(输入输出)
         */
        void addStudent(in Student student);

        List<Student> getStudentList();
}
