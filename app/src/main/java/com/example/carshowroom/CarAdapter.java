package com.example.carshowroom;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private List<car_type> cars;
    private SharedPreferences favouritesPreferences;

    public CarAdapter(List<car_type> cars, SharedPreferences favouritesPreferences) {
        this.cars = cars;
        this.favouritesPreferences = favouritesPreferences;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        car_type car = cars.get(position);
        if (car != null) {
            holder.companyName.setText(car.getCompanyName());
            holder.carName.setText(car.getCarName());
            holder.modelYear.setText(String.valueOf(car.getModel()));
            holder.maker.setText(car.getMaker());
            holder.imageCarLogo.setImageResource(car.getCarLogo());

            // Handle reset button click to remove the specific item
            holder.buttonResetCard.setOnClickListener(v -> {
                // Remove the car from the list
                cars.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cars.size()); // This ensures the positions are updated

                // Update the SharedPreferences
                saveFavouritesToPreferences();
            });

        }
    }



    @Override
    public int getItemCount() {
        return cars.size();
    }

    private void saveFavouritesToPreferences() {
        StringBuilder sb = new StringBuilder();
        for (car_type car : cars) {
            sb.append(car.getCompanyName()).append(",")
                    .append(car.getCarName()).append(",")
                    .append(car.getModel()).append(",")
                    .append(car.getMaker()).append(",")
                    .append(car.getCarLogo()).append(";");
        }

        SharedPreferences.Editor editor = favouritesPreferences.edit();
        editor.putString("favourites_data", sb.toString());
        editor.apply();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView companyName, carName, modelYear, maker;
        ImageView imageCarLogo;
        ImageButton buttonResetCard;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyName = itemView.findViewById(R.id.textViewCompanyName);
            carName = itemView.findViewById(R.id.textViewCarName);
            modelYear = itemView.findViewById(R.id.textViewModel);
            maker = itemView.findViewById(R.id.textViewMaker);
            imageCarLogo = itemView.findViewById(R.id.imageCarLogo);
            buttonResetCard = itemView.findViewById(R.id.buttonResetCard); // The reset button

        }
    }

}
