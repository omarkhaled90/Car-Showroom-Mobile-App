package com.example.carshowroom;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InstructionsActivity extends AppCompatActivity {

    public static final String LANGUAGE_PREF = "language_pref";
    public static final String LANGUAGE_KEY = "language_key";
    TextView tvFirst,tvSecond,tvThird,tvFourth,tvFifth,tvSixth,tvSeventh;
    TextView tvFunction, tvFirstDetails,tvSecondDetails,tvThirdDetails,tvFourthDetails,
            tvFifthDetails,tvSixthDetails,tvSeventhDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instructions);

        tvFirst=findViewById(R.id.tvFirst);
        tvSecond=findViewById(R.id.tvSecond);
        tvThird=findViewById(R.id.tvThird);
        tvFourth=findViewById(R.id.tvFourth);
        tvFifth=findViewById(R.id.tvFifth);
        tvSixth=findViewById(R.id.tvsixth);
        tvSeventh=findViewById(R.id.tvSeventh);
        tvFunction=findViewById(R.id.tvFunction);

        tvFirstDetails=findViewById(R.id.tvFirstDetails);
        tvSecondDetails=findViewById(R.id.tvSecondDetails);
        tvThirdDetails=findViewById(R.id.tvThirdDetails);
        tvFourthDetails=findViewById(R.id.tvFourthDetails);
        tvFifthDetails=findViewById(R.id.tvFifthDetails);
        tvSixthDetails=findViewById(R.id.tvsixthDetails);
        tvSeventhDetails=findViewById(R.id.tvSeventhDetails);


        // Get the language preference
        SharedPreferences sharedPreferences = getSharedPreferences(LANGUAGE_PREF, MODE_PRIVATE);
        boolean isArabic = sharedPreferences.getBoolean(LANGUAGE_KEY, false);

        // Set the text based on the language preference
        if (isArabic) {
            tvFirst.setText(" اولا "); // Arabic text
            tvSecond.setText(" ثانيا ");
            tvThird.setText(" ثالثا ");
            tvFourth.setText(" رابعا ");
            tvFifth.setText(" خامسا ");
            tvSixth.setText(" سادسا ");
            tvSeventh.setText(" سابعا ");
            tvFirstDetails.setText(" التطبيق يحتوي علي جميع شركات السيارات "); // Arabic text
            tvSecondDetails.setText(" التطبيق يحتوي علي اكثر من تصميم سيارة ");
            tvThirdDetails.setText(" التطبيق يعطي تفاصيل عن اي سيارة في الاجنس  ");
            tvFourthDetails.setText(" التطبيق يستطيع ايصالك لاقرب توكيل للسيارة بالنسبة لموقعك الحالي ");
            tvFifthDetails.setText(" تستطيع اضافة اي سيارة لقائمة المفضل للمقارنة ويمكن استبعاد الاخر و ازالته من القائمة ");
            tvSixthDetails.setText(" التطبيق يدعم اللغة العربية و الانجليزية ");
            tvSeventhDetails.setText(" التطبيق سريع و استجابته ممتازة ");
            tvFunction.setText(" وظائف تطبيق الاجنس للسيارات ؟");

        } else {
            tvFirst.setText(" First ");
            tvSecond.setText(" Second ");
            tvThird.setText(" Third ");
            tvFourth.setText(" Fourth ");
            tvFifth.setText(" Fifth ");
            tvSixth.setText(" Sixth ");
            tvSeventh.setText(" Seventh ");
            tvFirstDetails.setText("  App contains all Cars Companies  ");
            tvSecondDetails.setText(" ِApps Contains More than one Car Model ");
            tvThirdDetails.setText(" App gives Details about any Car inside Showroom ");
            tvFourthDetails.setText(" App can give you the nearest Dealer Ship for each Car  depending on your Current location ");
            tvFifthDetails.setText(" Any Car you Like can be added to the Favourite for comparing also can be removedAny Car you Like can be added to the Favourite for comparing also can be removed ");
            tvSixthDetails.setText(" App supports both English and Arabic Languages ");
            tvSeventhDetails.setText(" App has High response with Zero Delay ");
            tvFunction.setText(" Functions of Car Showroom App ? ");
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}