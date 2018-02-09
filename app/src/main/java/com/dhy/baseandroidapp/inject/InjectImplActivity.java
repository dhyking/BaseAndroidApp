package com.dhy.baseandroidapp.inject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhy.baseandroidapp.R;

@LayoutInject(R.layout.activity_inject)
public class InjectImplActivity extends InjectActivity {

    @ViewInject(R.id.tv_show)
    TextView textShow;
    @ViewInject(R.id.edt_input)
    EditText edtInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textShow.setText("show textview");
        edtInput.setText("show editext view");

        Test test = new Test();
    }


    private void testParam(@NonNull String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }







}
