package in.co.foodamigo.supplier.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.co.foodamigo.supplier.R;

/**
 * Created by gts on 20/11/15.
 */
public class MainActivity extends AppCompatActivity{
    //AbstractActivity


    EditText epn, ean, ecn1, ead1, ead2, eam, ept;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        epn = (EditText) findViewById(R.id.epn);
        ean = (EditText) findViewById(R.id.ean);
        ecn1 = (EditText) findViewById(R.id.ecn1);
        ead1 = (EditText) findViewById(R.id.ead1);
        ead2 = (EditText) findViewById(R.id.ead2);
        eam = (EditText) findViewById(R.id.eam);
        ept = (EditText) findViewById(R.id.ept);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                //Create a bundle object
                Bundle b = new Bundle();
                b.putString("customer number", epn.getText().toString());
                b.putString("alternate number", ean.getText().toString());
                b.putString("customer name", ecn1.getText().toString());
                b.putString("add1", ead1.getText().toString());
                b.putString("add2", ead2.getText().toString());
                b.putString("amount", eam.getText().toString());
                b.putString("pick up", ept.getText().toString());
                intent.putExtras(b);

                //start the DisplayActivity
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        Log.i("open a fragmen","open a fragment");
//        switch (item.getItemId()) {
//            case R.id.login:
//
//                Log.i(" Login fragment","login fragment");
//                Bundle b = new Bundle();
//                EventBus.getDefault().post(new ChangeContentEvent(ChangeContentEvent.ContentType.FRAGMENT, b) {
//                    @Override
//                    public Class getContentClass() {
//                        return loginfragment.class;
//                    }
//                });
//                break;


return super.onOptionsItemSelected(item);
    }
//
//
//    protected int getLayoutId() {
//        return R.layout.layout;
//    }
//
//    protected int getDrawerFragmentId() {
//        return R.id.navigation_drawer;
//    }
//
//    protected int getToolbarId() {
//        return R.id.toolbar;
//    }
//
//    protected int getContentContainerId() {
//        return R.id.container;
//    }
//
//    protected Fragment getInitContent() {
//        return new loginfragment();
//    }
//
//    protected DrawerLayout getDrawerLayout() {
//        return (DrawerLayout) findViewById(R.id.drawer_layout);
//    }

    }


