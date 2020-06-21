package com.example.xando;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Generate generate=new Generate();
        final ArrayList<String> arrayList=new ArrayList<>();
        char[][] c=new char[3][3];
        for(int i=0;i<3;i++)for(int j=0;j<3;j++)c[i][j]=' ';
        generate.traverse(c, new ArrayList<String>(),1);


        final Button[][] buttons=new Button[3][3];
        buttons[0][0]=(Button)findViewById(R.id.b1);
        buttons[0][1]=(Button)findViewById(R.id.b2);
        buttons[0][2]=(Button)findViewById(R.id.b3);
        buttons[1][0]=(Button)findViewById(R.id.b4);
        buttons[1][1]=(Button)findViewById(R.id.b5);
        buttons[1][2]=(Button)findViewById(R.id.b6);
        buttons[2][0]=(Button)findViewById(R.id.b7);
        buttons[2][1]=(Button)findViewById(R.id.b8);
        buttons[2][2]=(Button)findViewById(R.id.b9);

        List<String> check=new ArrayList<>();
        for( int i=0;i<3;i++){
            for( int j=0;j<3;j++){
                buttons[i][j].setText("");
                final int finalI = i;
                final int finalJ = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(arrayList.size()%2==0)buttons[finalI][finalJ].setText("X");
                        else buttons[finalI][finalJ].setText("O");
                        arrayList.add(finalI+" "+finalJ);
                        String d=generate.Probability(arrayList);
                        if(arrayList.size()%2==0)
                        Toast.makeText(MainActivity.this, ""+d, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}