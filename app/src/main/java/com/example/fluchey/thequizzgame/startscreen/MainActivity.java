package com.example.fluchey.thequizzgame.startscreen;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fluchey.thequizzgame.R;
import com.example.fluchey.thequizzgame.gamescreen.view.GameActivity;

public class MainActivity extends AppCompatActivity {
    private ListView category;

    private ArrayAdapter<String> categoryAdapter;
    private NumberPicker numberPicker;
    private RadioGroup radioGroup;

    private TextView categoryChoosen;
    private TextView numberOfQuestionsChoosen;
    private TextView difficultyChoosen;

    int postionInArrayForCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryChoosen = (TextView) findViewById(R.id.tvCategoryChoose);
        difficultyChoosen = (TextView) findViewById(R.id.tvDifficultyChoosen);
        numberOfQuestionsChoosen = (TextView) findViewById(R.id.tvQuestionsChoosen);

        category = (ListView) findViewById(R.id.categoryListview);
        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categories));
        category.setAdapter(categoryAdapter);
        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoryChoosen.setText(categoryAdapter.getItem((int) id));
                postionInArrayForCategory = (int) id;
                Log.d("PostionArrayCategory", String.valueOf(postionInArrayForCategory + 8));
            }
        });

        numberOfQuestionsChoosen.setText("1");
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(20);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                numberOfQuestionsChoosen.setText(String.valueOf(newVal));
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.difficultyGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                difficultyChoosen.setText(radioButton.getText());
            }
        });

    }

    public void startNewGame(View view) {
        if (categoryChoosen.getText().toString().isEmpty()){
            Toast.makeText(this, "Choose a category", Toast.LENGTH_SHORT).show();
        } else if(difficultyChoosen.getText().toString().isEmpty()){
            Toast.makeText(this, "Choose a difficulty", Toast.LENGTH_SHORT).show();
        }else {
            Intent startNewGameIntent = new Intent(MainActivity.this, GameActivity.class);

            /**
             * +8 is because the API format in the URL starts with category number 9
             */
            startNewGameIntent.putExtra("category", String.valueOf(postionInArrayForCategory + 8));
            startNewGameIntent.putExtra("numberOfQuestions", numberOfQuestionsChoosen.getText().toString());
            startNewGameIntent.putExtra("difficulty", difficultyChoosen.getText().toString());
            MainActivity.this.startActivity(startNewGameIntent);
        }
    }
}
