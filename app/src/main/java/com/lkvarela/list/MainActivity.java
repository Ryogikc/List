package com.lkvarela.list;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private AvisosDBAdapter mDbAdapter;
    private AvisosSimpleCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.avisos_list);
        findViewById(R.id.avisos_list);
        mListView.setDivider(null);
        mDbAdapter = new AvisosDBAdapter(this);
        mDbAdapter.open();



        Cursor cursor = mDbAdapter.fetchAllReminders();

        //desde las columnas definidas en la base de datos
        String[] from = new String[]{
                AvisosDBAdapter.COL_CONTENT
        };

        //a la id de views en el layout
        int[] to = new int[]{
                R.id.row_list
        };

        mCursorAdapter = new AvisosSimpleCursorAdapter(
                //context
                MainActivity.this,
                //el layout de la fila
                R.layout.aviso_row,
                //cursor
                cursor,
                //desde columnas definidas en la base de datos
                from,
                //a las ids de views en el layout
                to,
                //flag - no usado
                0);

        //el cursorAdapter (controller) está ahora actualizando la listView (view)
        //con datos desde la base de datos (modelo)
        mListView.setAdapter(mCursorAdapter);


// cuando pulsamos un item individual en la  listview
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, final int masterListPosition, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                ListView modeListView = new ListView(MainActivity.this);
                String[] modes = new String[]{"Editar Aviso", "Borrar Aviso"};
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, modes);
                modeListView.setAdapter(modeAdapter);
                builder.setView(modeListView);
                final Dialog dialog = builder.create();
                dialog.show();
                modeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //editar aviso
                        if (position == 0) {
                            Toast.makeText(MainActivity.this, "editar "
                                    + masterListPosition, Toast.LENGTH_SHORT).show();
                            //borrar aviso
                        } else {
                            Toast.makeText(MainActivity.this, "borrar "
                                    + masterListPosition, Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){

            case R.id.aviso:
                Log.d(getLocalClassName(), getString(R.string.crear_aviso));
                return true;

            case R.id.salir:
                finish();
                return true;

            default:
                return false;





        }



    }
}
