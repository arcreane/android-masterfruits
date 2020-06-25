package com.example.masterfruits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    ImageView[] slots ;
    ImageView selectedImage ;
    Button Replay ;
    Button Hint;
    List<Pair<Drawable[],String>> History;
    RecyclerView.Adapter adapter;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //ImageView slot1 = findViewById(R.id.slot1);

        //Gestion des images à choisir en entrée
        slots = new ImageView[4];
            for (int i = 1; i < 5; i++){
                String name = "slot"+i;
                int id = getResources().getIdentifier(name, "id", this.getPackageName());
               // ImageView slot = findViewById(id);
                slots[i-1] = findViewById(id);
                registerForContextMenu(slots[i-1]);
            }

            //Action du bouton replay (redémarre l'écran à 0)
            Replay = findViewById(R.id.replay);
            Replay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent gameIntent = new Intent(GameActivity.this , GameActivity.class);
                    startActivity(gameIntent);
                }
            });

            //Action du bouton indice (ouverture menu indice)
            Hint = findViewById(R.id.hint);
            Hint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        PopupMenu popup = new PopupMenu(GameActivity.this, v);
                        MenuInflater inflater = popup.getMenuInflater();
                        inflater.inflate(R.menu.hint, popup.getMenu());
                        popup.show();

                }
            });

            //Gestion du recycler view
            History = new ArrayList<>();
            adapter = new Result(this, History);
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setAdapter(adapter);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);

            game = new Game();
            game.startGame();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.fruit_choice, menu);
        selectedImage = (ImageView) v;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //return super.onContextItemSelected(item);
        selectedImage.setImageBitmap(((BitmapDrawable) item.getIcon()).getBitmap());
        selectedImage.setTag(item.getTitle());
        return true;
    }

    public void validatePressed(View view) {
        Drawable[] tmp = new Drawable[4];
        String recupContent = "";
        for(int i=0; i < 4; i++){
             tmp[i] = slots[i].getDrawable();
            recupContent += (String)slots[i].getTag();
            if(i < 3) {
                recupContent += "-";
            }
        }
        game.play(recupContent);
        History.add(0, new Pair<Drawable[], String>(tmp, "toto"));
        adapter.notifyDataSetChanged();
    }
}