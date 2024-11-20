package com.example.carshowroom;

import static com.example.carshowroom.SettingsFragment.LANGUAGE_KEY;
import static com.example.carshowroom.SettingsFragment.LANGUAGE_PREF;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class CarDetailsActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private SharedPreferences sharedPreferences;
    private SharedPreferences favouritesPreferences; // For favourites

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    ImageView carLogoImageView;
    TextView textViewCompanyName, textViewCarName, textViewModel, textViewMaker, textLocationLabel;
    ImageButton buttonLocation,buttonLocations;


    String companyName, carName, maker;
    int modelYear, carLogoResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_car_details);

        // Initialize the location client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Initialize the SharedPreferences for saving favourite data
        sharedPreferences = getSharedPreferences(LANGUAGE_PREF, MODE_PRIVATE);

        favouritesPreferences = getSharedPreferences("favourites_data", MODE_PRIVATE); // Separate for favourites


        // Initialize UI components
        carLogoImageView = findViewById(R.id.carLogoImageView);
        textViewCompanyName = findViewById(R.id.textViewCompanyName);
        textViewCarName = findViewById(R.id.textViewCarName);
        textViewModel = findViewById(R.id.textViewModel);
        textViewMaker = findViewById(R.id.textViewMaker);
        buttonLocation = findViewById(R.id.buttonLocation);
        textLocationLabel = findViewById(R.id.textLocationLabel);
        buttonLocations = findViewById(R.id.buttonLocations);


        ImageButton buttonAddToFavourite = findViewById(R.id.AddtoFavourite);
        buttonAddToFavourite.setOnClickListener(v -> {
            saveToFavourites();
            Toast.makeText(this, "Added to Favourites", Toast.LENGTH_SHORT).show();
        });

//         Retrieve data from Intent
        companyName = getIntent().getStringExtra("companyName");
        carName = getIntent().getStringExtra("carName");
        modelYear = getIntent().getIntExtra("modelYear", 0);
        maker = getIntent().getStringExtra("maker");
        carLogoResId = getIntent().getIntExtra("carLogo", 0);

        setLanguageTexts();

        carLogoImageView.setImageResource(carLogoResId);

        buttonLocations.setOnClickListener(v -> {
            // Log to verify if the click is registered
            Log.d("CarDetailsActivity", "Location button clicked");

            // Get the dealership location query
            String locationQuery = getAgencyLocation(companyName);
            if (locationQuery != null) {
                // Log the location query
                Log.d("CarDetailsActivity", "Location query: " + locationQuery);

                // Open the MapWebViewActivity to show the map
                Intent intent = new Intent(CarDetailsActivity.this, MapWebViewActivity.class);
                intent.putExtra("locationQuery", locationQuery);
                startActivity(intent);
            } else {
                Log.d("CarDetailsActivity", "No location found for company: " + companyName);
            }
        });

        buttonLocation.setOnClickListener(v -> {
            // Request location if permission is granted
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                getCurrentLocationAndNavigate();
            }
        });

        // Listen for changes in language preference
        preferenceChangeListener = (sharedPreferences, key) -> {
            if (LANGUAGE_KEY.equals(key)) {
                setLanguageTexts();

            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocationAndNavigate();
        }
    }

    private void getCurrentLocationAndNavigate() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        String destination = getAgencyLocation(companyName);

                        if (destination != null) {
                            // Create a navigation URI to open in Google Maps
                            String navigationUri = "google.navigation:q=" + destination + "&saddr=" + latitude + "," + longitude;
                            Uri uri = Uri.parse(navigationUri);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            intent.setPackage("com.google.android.apps.maps");
                            startActivity(intent);
                        } else {
                            Toast.makeText(this, "No dealership location found for the selected company.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Unable to get current location", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to get current location", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    private void saveToFavourites() {
        SharedPreferences.Editor editor = favouritesPreferences.edit();
        String favouritesData = favouritesPreferences.getString("favourites_data", "");

        favouritesData += companyName + "," + carName + "," + modelYear + "," + maker + "," + carLogoResId + ";";
        editor.putString("favourites_data", favouritesData);
        editor.apply();
    }

    private void setLanguageTexts() {
        boolean isArabic = sharedPreferences.getBoolean(LANGUAGE_KEY, false);

        if (isArabic) {
            textViewCompanyName.setText(" اسم الشركة : " + companyName);
            textViewCarName.setText(" اسم السيارة : " + carName);
            textViewModel.setText(" سنة الصنع: " + modelYear);
            textViewMaker.setText(" الشركة المصنعة: " + maker);
            buttonLocation.setContentDescription(" أقرب وكيل: ");
            textLocationLabel.setText(" أقرب وكيل: ");
        } else {
            textViewCompanyName.setText(" Company name: " + companyName);
            textViewCarName.setText(" Car name: " + carName);
            textViewModel.setText(" Model Year: " + modelYear);
            textViewMaker.setText(" Maker: " + maker);
            buttonLocation.setContentDescription(" Nearest Dealership ");
            textLocationLabel.setText(" Nearest Dealership ");

        }
    }




    // Get agency location based on the company name
    private String getAgencyLocation(String companyName) {
        switch (companyName.toLowerCase()) {
            case "hyundai":
                return "Hyundai dealership near me";
            case "bmw":
                return "BMW dealership near me";
            case "fiat":
                return "Fiat dealership near me";
            case "kia":
                return "KIA dealership near me";
            case "chevrolet":
                return "Chevrolet dealership near me";
            case "honda":
                return "Honda dealership near me";
            case "toyota":
                return "Toyota dealership near me";
            case "dodge":
                return "Dodge dealership near me";
            case "ford":
                return "Ford dealership near me";
            case "citroen":
                return "Citroen dealership near me";
            case "lancer":
                return "Lancer dealership near me";
            case "nissan":
                return "Nissan dealership near me";
            case "suzuki":
                return "Suzuki dealership near me";
            case "skoda":
                return "Skoda dealership near me";
            case "subaru":
                return "Subaru dealership near me";
            case "renault":
                return "Renault dealership near me";
            case "volvo":
                return "Volvo dealership near me";
            case "opel":
                return "Opel dealership near me";
            default:
                return null;
        }
    }

}