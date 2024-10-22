package com.example.kopapirollo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewYourChoice;
    private ImageView imageViewComputerChoice;

    private TextView textViewYourChoice;
    private TextView textViewComputerChoice;

    private Button buttonRock;
    private Button buttonPaper;
    private Button buttonScissors;

    private TextView textViewResult;



    private int yourChoice;
    private int computerChoice;

    private int wins;
    private int losses;

    private AlertDialog alertDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();


        buttonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewYourChoice.setImageResource(R.drawable.rock);
                yourChoice = 0;
                winLogic();
            }
        });

        buttonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewYourChoice.setImageResource(R.drawable.paper);
                yourChoice = 1;
                winLogic();
            }
        });

        buttonScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViewYourChoice.setImageResource(R.drawable.scissors);
                yourChoice = 2;
                winLogic();
            }
        });

    }

    private void winLogic() {
        Random random = new Random();
        computerChoice = random.nextInt(3);
        ComputerSetImage();
        // 0 = rock, 1 = paper, 2 = scissors
        if (wins < 3 || losses < 3) {
            if(yourChoice == 0 && computerChoice == 2) {
                wins++;
                textViewResult.setText("Eredmény: Ember: " + wins + " Computer: " + losses);
                Toast.makeText(MainActivity.this, "Nyerted a kört!", Toast.LENGTH_SHORT).show();
            }
            else if(yourChoice == 1 && computerChoice == 0) {
                wins++;
                textViewResult.setText("Eredmény: Ember: " + wins + " Computer: " + losses);
                Toast.makeText(MainActivity.this, "Nyerted a kört!", Toast.LENGTH_SHORT).show();

            }
            else if(yourChoice == 2 && computerChoice == 1) {
                wins++;
                textViewResult.setText("Eredmény: Ember: " + wins + " Computer: " + losses);
                Toast.makeText(MainActivity.this, "Nyerted a kört!", Toast.LENGTH_SHORT).show();

            }
            else if(yourChoice == computerChoice) {
                textViewResult.setText("Eredmény: Ember: " + wins + " Computer: " + losses);
                Toast.makeText(MainActivity.this, "Nyerted a kört!", Toast.LENGTH_SHORT).show();

            }
            else {
                losses++;
                textViewResult.setText("Eredmény: Ember: " + wins + " Computer: " + losses);
                Toast.makeText(MainActivity.this, "Vesztetted a kört!", Toast.LENGTH_SHORT).show();
            }
        }
        if(wins == 3) {
            alertDialog.setTitle("Győzelem!");
            alertDialog.show();
        }
        else if(losses == 3) {
            alertDialog.setTitle("Vereség!");
            alertDialog.show();
        }

    }




    private void ComputerSetImage() {
        if (computerChoice == 0) {
            imageViewComputerChoice.setImageResource(R.drawable.rock);
        }
        else if (computerChoice == 1) {
            imageViewComputerChoice.setImageResource(R.drawable.paper);
        }
        else {
            imageViewComputerChoice.setImageResource(R.drawable.scissors);
        }
    }

    private void init() {
        imageViewYourChoice = findViewById(R.id.imageViewYourChoice);
        imageViewComputerChoice = findViewById(R.id.imageViewComputerChoice);

        textViewYourChoice = findViewById(R.id.textViewYourChoice);
        textViewComputerChoice = findViewById(R.id.textViewComputerChoice);

        buttonRock = findViewById(R.id.buttonRock);
        buttonPaper = findViewById(R.id.buttonPaper);
        buttonScissors = findViewById(R.id.buttonScissors);

        textViewResult = findViewById(R.id.textViewResult);

        wins = 0;
        losses = 0;
        yourChoice = 0;
        computerChoice = 0;


        alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Győzelem!")
                .setMessage("Szeretne új játékot játszani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        wins = 0;
                        losses = 0;
                        textViewResult.setText("Eredmény: Ember: " + wins + " Computer: " + losses);
                    }
                }).create();
    }
}