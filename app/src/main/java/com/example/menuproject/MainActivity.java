package com.example.menuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Ejemplo de menú principal y contextual en Android
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvTexto2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //obtengo la referencia al TextView
        TextView tvTexto1 = findViewById(R.id.tvTexto1);
        //la referencia al segundo cuadro de texto se guarda
        //en una propiedad de la clase
        this.tvTexto2 = findViewById(R.id.tvTexto2);
        //relaciono el TextView con el menú contextual
        registerForContextMenu(tvTexto1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        //se obtiene la referencia a la opción de menú que contendrá el submenú
        MenuItem opcmenu1 = menu.findItem(R.id.opcmenu1);
        //se carga el submenú main_submenu en la opcion anterior
        getMenuInflater().inflate(R.menu.main_submenu, opcmenu1.getSubMenu());
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_submenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.opcmenu1:
                Toast.makeText(this,R.string.guarda_cambios,Toast.LENGTH_LONG).show();
                break;
            case R.id.opcmenu2:
                if(this.tvTexto2.getVisibility()==View.VISIBLE) {
                    this.tvTexto2.setVisibility(View.INVISIBLE);
                    item.setTitle(R.string.opc2_ver);
                }else{
                    this.tvTexto2.setVisibility(View.VISIBLE);
                    item.setTitle(R.string.opc2_ocultar);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcionSub1:
                Toast.makeText(getApplicationContext(),R.string.contex_menu_op1, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcionSub2:
                Toast.makeText(getApplicationContext(), R.string.contex_menu_op2, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}