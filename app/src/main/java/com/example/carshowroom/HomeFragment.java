package com.example.carshowroom;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomeFragment extends Fragment {

    private ImageView imageHyundai, imageBMW, imageFiat,imageKIA,imageChevorlet,imageHonda,
            imageToyota,imageDodge,imageFord,imageNissan,imageLancer,imageCitroen,
            imageSuzuki,imageSkoda,imageSubaru,imageRenault,imageVolvo,imageOpel;
    private List<car_type> cars;
    private List<car_type> filteredCars;

    private Dialog dialog;

  public HomeFragment()
  {
      super(R.layout.fragment_home);
  }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize car companies logos
        imageHyundai =view.findViewById(R.id.imageHyundai);
        imageBMW = view.findViewById(R.id.imageBMW);
        imageFiat = view.findViewById(R.id.imageFiat);
        imageKIA=view.findViewById(R.id.imageKIA);
        imageChevorlet=view.findViewById(R.id.imageChevrolet);
        imageHonda=view.findViewById(R.id.imageHonda);
        imageToyota=view.findViewById(R.id.imageToyota);
        imageDodge=view.findViewById(R.id.imageDodge);
        imageFord=view.findViewById(R.id.imageFord);
        imageNissan=view.findViewById(R.id.imageNissan);
        imageLancer=view.findViewById(R.id.imageLancer);
        imageCitroen=view.findViewById(R.id.imageCitroen);
        imageSuzuki=view.findViewById(R.id.imageSuzuki);
        imageSkoda=view.findViewById(R.id.imageSkoda);
        imageSubaru=view.findViewById(R.id.imageSubaru);
        imageRenault=view.findViewById(R.id.imageRenault);
        imageVolvo=view.findViewById(R.id.imageVolvo);
        imageOpel=view.findViewById(R.id.imageOpel);


        cars = new ArrayList<>(Arrays.asList(

                //hyundai
                new car_type("Hyundai", "Verna", 2009, "South Korea", R.drawable.verna),
                new car_type("Hyundai", "Elantra", 2022, "South Korea", R.drawable.elentra),
                new car_type("Hyundai", "Tucson", 2020, "South Korea", R.drawable.tuscan),
                new car_type("Hyundai", "Sonata", 2012, "South Korea", R.drawable.sonta),
                new car_type("Hyundai", "Matrix", 2009, "South Korea", R.drawable.matrix),

                //Kia
                new car_type("KIA", "Cerato", 2024, "South Korea", R.drawable.cerato),
                new car_type("KIA", "Sportage", 2024, "South Korea", R.drawable.sportage),

                //Chevrolet
                new car_type("Chevrolet", "Cruz", 2009, "USA", R.drawable.cruz),
                new car_type("Chevrolet", "Traverse", 2009, "USA", R.drawable.traverse),
                //Honda
                new car_type("Honda", "Civic", 2009, "Japan", R.drawable.hondacivic),
                new car_type("Honda", "Passport", 2009, "Japan", R.drawable.hondapassport),



                //Fiat
                new car_type("Fiat", "500", 2018, "Italy", R.drawable.fiat500),
                new car_type("Fiat", "Tipo", 2018, "Italy", R.drawable.fiattipo),
                //BMW
                new car_type("BMW", "X5", 2018, "Germany", R.drawable.bmwx5),
                new car_type("BMW", "X6", 2018, "Germany", R.drawable.bmwx6),

                //Toyota
                new car_type("Toyota", "Corolla", 2018, "Japan", R.drawable.corola),
                new car_type("Toyota", "Yaris-Cross", 2018, "Japan", R.drawable.yaris),
                //Dodge
                new car_type("Dodge", "Chalenger", 2018, "Japan", R.drawable.dodgechalenger),
                new car_type("Dodge", "Ram", 2018, "Japan", R.drawable.dodgeram),
                //Ford
                new car_type("Ford", "Mustange", 2018, "Japan", R.drawable.fordmustange),
                new car_type("Ford", "Explorer", 2018, "Japan", R.drawable.fordexplorer),
                //Citroen
                new car_type("Citroen", "C3", 2024, "France", R.drawable.citroends5),
                new car_type("Citroen", "DS5", 2023, "France", R.drawable.citroends5),
                //Lancer
                new car_type("Lancer", "Eclipse Cross", 2024, "France", R.drawable.eclipsecross),
                new car_type("Lancer", "Mirage", 2023, "France", R.drawable.mirage),
                //Nissan
                new car_type("Nissan", "Sentra", 2024, "France", R.drawable.sentra),
                new car_type("Nissan", "Qashqai", 2023, "France", R.drawable.qashqai),

                //Suzuki
                new car_type("Suzuki", "Swift", 2024, "France", R.drawable.suzukiswift),
                new car_type("Suzuki", "S-Presso", 2023, "France", R.drawable.suzukispresso),

                //Skoda
                new car_type("Skoda", "Kushak", 2024, "France", R.drawable.skodakushak),
                new car_type("Skoda", "Octavia", 2023, "France", R.drawable.skodaoctavia),
                //Subaru
                new car_type("Subaru", "CrossTrek", 2024, "France", R.drawable.subarocrosstrek),
                new car_type("Subaru", "Legacy", 2023, "France", R.drawable.subarolegacy),
                //Renault
                new car_type("Renault", "Stepway", 2024, "France", R.drawable.stepway),
                new car_type("Renault", "Megane", 2023, "France", R.drawable.renaultmegane),
                //Volvo
                new car_type("Volvo", "S60", 2024, "France", R.drawable.volvos60),
                new car_type("Volvo", "V90", 2023, "France", R.drawable.volvov90),
                //Opel
                new car_type("Opel", "Corsa", 2024, "France", R.drawable.opelcorsa),
                new car_type("Opel", "Insigia", 2023, "France", R.drawable.opelinsigia)


        ));

        // Set click listeners for each car company logo
        imageHyundai.setOnClickListener(v -> showCarListDialog("Hyundai"));
        imageBMW.setOnClickListener(v -> showCarListDialog("BMW"));
        imageFiat.setOnClickListener(v -> showCarListDialog("Fiat"));
        imageKIA.setOnClickListener(v -> showCarListDialog("KIA"));
        imageChevorlet.setOnClickListener(v -> showCarListDialog("Chevrolet"));
        imageHonda.setOnClickListener(v -> showCarListDialog("Honda"));
        imageToyota.setOnClickListener(v -> showCarListDialog("Toyota"));
        imageDodge.setOnClickListener(v -> showCarListDialog("Dodge"));
        imageFord.setOnClickListener(v -> showCarListDialog("Ford"));
        imageCitroen.setOnClickListener(v -> showCarListDialog("Citroen"));
        imageLancer.setOnClickListener(v -> showCarListDialog("Lancer"));
        imageNissan.setOnClickListener(v -> showCarListDialog("Nissan"));

        imageSuzuki.setOnClickListener(v -> showCarListDialog("Suzuki"));
        imageSkoda.setOnClickListener(v -> showCarListDialog("Skoda"));
        imageSubaru.setOnClickListener(v -> showCarListDialog("Subaru"));
        imageRenault.setOnClickListener(v -> showCarListDialog("Renault"));
        imageVolvo.setOnClickListener(v -> showCarListDialog("Volvo"));
        imageOpel.setOnClickListener(v -> showCarListDialog("Opel"));

    }

    private void showCarListDialog(String companyName) {
        // Filter cars by company name
        filteredCars = new ArrayList<>();
        for (car_type car : cars) {
            if (car.getCompanyName().equals(companyName)) {
                filteredCars.add(car);
            }
        }

        // Show a dialog with the list of available cars for the selected company
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_searchable_spinner);
        dialog.getWindow().setLayout(650, 800);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        EditText editText = dialog.findViewById(R.id.edit_text);
        ListView listView = dialog.findViewById(R.id.listView);

        // Create a list of car names for the filtered cars
        List<String> carNames = new ArrayList<>();
        for (car_type car : filteredCars) {
            carNames.add(car.getCarName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, carNames);
        listView.setAdapter(adapter);

        // Filter cars based on user input
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        // When a car is selected, show its details
        listView.setOnItemClickListener((parent, view, position, id) -> {
            car_type selectedCar = filteredCars.get(position);
            showCarDetails(selectedCar);
            dialog.dismiss();
        });
    }

    private void showCarDetails(car_type selectedCar) {
        Intent intent = new Intent(getActivity(), CarDetailsActivity.class);
        intent.putExtra("companyName", selectedCar.getCompanyName());
        intent.putExtra("carName", selectedCar.getCarName());
        intent.putExtra("modelYear", selectedCar.getModel());
        intent.putExtra("maker", selectedCar.getMaker());
        intent.putExtra("carLogo", selectedCar.getCarLogo());
        startActivity(intent);
    }
}