package com.example.uchetperevozki;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class NewReport2 extends AppCompatActivity {

    private static final String TAG = "NewReport2";

    private RadioGroup radioGroupView;
    private RadioGroup radioGroupLong;
    private Spinner spinnerAssortment;
    private Spinner spinnerVariety;
    private EditText editTextVolumeWood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_report2);
        initViews();
        setupSpinners();
        setupButtonClickListener();
    }

    private void initViews() {
        radioGroupView = findViewById(R.id.radioGroupView);
        radioGroupLong = findViewById(R.id.radioGroupLong);
        spinnerAssortment = findViewById(R.id.fillAssortment);
        spinnerVariety = findViewById(R.id.fillVarietyWood);
        editTextVolumeWood = findViewById(R.id.fillVolume);
    }

    private void setupSpinners() {
        String[] itemAssortment = {"Берёза", "Ольха", "Сосна", "Осина"};
        String[] itemVariety = {"1 сорт", "2 сорт", "3 сорт"};

        ArrayAdapter<String> adapterAssortment = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemAssortment);
        adapterAssortment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAssortment.setAdapter(adapterAssortment);

        ArrayAdapter<String> adapterVariety = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemVariety);
        adapterVariety.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVariety.setAdapter(adapterVariety);

        // Установка слушателей для Spinner
        setupSpinnerListeners();
    }

    private void setupSpinnerListeners() {
        spinnerVariety.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String varietyWood = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(NewReport2.this, "Выбрано: " + varietyWood, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(NewReport2.this, "Заполните поле сорт древесины!", Toast.LENGTH_SHORT).show();
            }
        });

        spinnerAssortment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String assortmentWood = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(NewReport2.this, "Выбрано: " + assortmentWood, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(NewReport2.this, "Заполните поле ассортимент древесины!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupButtonClickListener() {
        Button buttonNext2 = findViewById(R.id.btnNextReport2);
        buttonNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivityReport2();
            }
        });
    }

    private boolean isAnyStringNull(String... strings) {
        for (String str : strings) {
            if (str == null) {
                return true;
            }
        }
        return false;
    }

    private String getViewWood() {
        int selectedIdView = radioGroupView.getCheckedRadioButtonId();
        if (selectedIdView != -1) {
            RadioButton selectedRadioButton = findViewById(selectedIdView);
            return selectedRadioButton.getText().toString();
        } else {
            Toast.makeText(this, "Выберите вид древесины!", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private int getLongWood() {
        int selectedIdLong = radioGroupLong.getCheckedRadioButtonId();
        if (selectedIdLong != -1) {
            RadioButton selectedRadioButton = findViewById(selectedIdLong);
            String selectedTextLong = selectedRadioButton.getText().toString();
            switch (selectedTextLong) {
                case "3 метра":
                    return 3;
                case "4 метра":
                    return 4;
                case "6 метров":
                    return 6;
                default:
                    Toast.makeText(this, "Неизвестная длина древесины!", Toast.LENGTH_SHORT).show();
                    return 0; // Возврат 0, если значение не соответствует
            }
        } else {
            Toast.makeText(this, "Выберите длину древесины!", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    private Float getVolumeWood() {
        String volumeWoodString = editTextVolumeWood.getText().toString().trim();
        if (volumeWoodString.isEmpty()) {
            Toast.makeText(this, "Введите объем древесины!", Toast.LENGTH_SHORT).show();
            return 0.0f; // Возврат 0 как безопасное значение
        } else {
            try {
                return Float.parseFloat(volumeWoodString);
            } catch (NumberFormatException e) {
                Log.e(TAG, "Ошибка парсинга объема древесины: " + e.getMessage());
                Toast.makeText(this, "Введите корректный объем древесины!", Toast.LENGTH_SHORT).show();
                return 0.0f; // Возврат 0 в случае некорректного ввода
            }
        }
    }

    private void nextActivityReport2() {
        // Получение данных с предыдущего экрана
        Intent previousIntent = getIntent();
        String departurePoint = previousIntent.getStringExtra("departure_point");
        String typeDeparturePoint = previousIntent.getStringExtra("type_departure_point");
        String sender = previousIntent.getStringExtra("sender");
        String targetPoint = previousIntent.getStringExtra("target_point");
        String typeTargetPoint = previousIntent.getStringExtra("type_target_point");
        String recipient = previousIntent.getStringExtra("recipient");

        // Проверка на null
        if (isAnyStringNull(departurePoint, typeDeparturePoint, sender, targetPoint, typeTargetPoint, recipient)) {
            Toast.makeText(this, "Не все данные заполнены!", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Не все данные заполнены!");
            return;
        }

        String viewWood = getViewWood();
        int longWood = getLongWood();
        String varietyWood = (String) spinnerVariety.getSelectedItem();
        String assortmentWood = (String) spinnerAssortment.getSelectedItem();
        Float volumeWood = getVolumeWood();

        // Проверка на заполненные поля
        if (viewWood == null || longWood <= 0 || volumeWood <= 0) {
            Toast.makeText(this, "Пожалуйста, заполните все необходимые поля!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Создание Intent для следующей активности
        Intent intent = new Intent(this, NewReport3.class);
        intent.putExtra("departure_point", departurePoint);
        intent.putExtra("view_wood", viewWood);
        intent.putExtra("long_wood", longWood);
        intent.putExtra("variety_wood", varietyWood);
        intent.putExtra("assortment_wood", assortmentWood);
        intent.putExtra("volume_wood", volumeWood);
        intent.putExtra("type_departure_point", typeDeparturePoint);
        intent.putExtra("sender", sender);
        intent.putExtra("target_point", targetPoint);
        intent.putExtra("type_target_point", typeTargetPoint);
        intent.putExtra("recipient", recipient);

        startActivity(intent);
    }



    public void backBtnReport2(View view) {
        finish();
    }



}