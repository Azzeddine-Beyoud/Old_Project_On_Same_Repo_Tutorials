package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    int index=0;
    int[] arimage=new int[]{R.drawable.a,R.drawable.b,R.drawable.c};

    public void Nextbu(View view){
        ImageView myimage=(ImageView)findViewById(R.id.imageV);
        myimage.setImageResource(arimage[index]);
        index++;
        if(index>2){
            index=0;
        }
    }

@Override
public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return true;

}
@Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id=item.getItemId();
        if (id==R.id.goback){
            Toast.makeText(this,"add is clicked",Toast.LENGTH_LONG).show();
        }

        if (id==R.id.home){
            Toast.makeText(this,"home is clicked",Toast.LENGTH_LONG).show();
        }
         return super.onOptionsItemSelected(item);
}

}

