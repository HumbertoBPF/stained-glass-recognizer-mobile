package com.example.stainedglassrecognizer.activities;

import static com.example.stainedglassrecognizer.utils.Imaging.getBitmapFromBase64;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stainedglassrecognizer.R;
import com.example.stainedglassrecognizer.models.StainedGlass;

public class ImageInfoActivity extends AppCompatActivity {

    private TextView artistTextView;
    private TextView yearBirthTextView;
    private TextView yearPassingTextView;
    private TextView artistReferenceTextView;
    private TextView glassDateTextView;
    private TextView dateReferenceTextView;
    private TextView iconographyTextView;
    private TextView churchNameTextView;
    private TextView urlTextView;

    private ImageView stainedGlassImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        artistTextView = findViewById(R.id.artist_text_view);
        yearBirthTextView = findViewById(R.id.year_birth_text_view);
        yearPassingTextView = findViewById(R.id.year_passing_text_view);
        artistReferenceTextView = findViewById(R.id.artist_reference_text_view);
        glassDateTextView = findViewById(R.id.glass_date_text_view);
        dateReferenceTextView = findViewById(R.id.date_reference_text_view);
        iconographyTextView = findViewById(R.id.iconography_text_view);
        churchNameTextView = findViewById(R.id.church_name_text_view);
        urlTextView = findViewById(R.id.url_text_view);
        stainedGlassImageView = findViewById(R.id.stained_glass_image_view);

        Intent intent = getIntent();
        StainedGlass stainedGlass = (StainedGlass) intent.getSerializableExtra("stainedGlass");

        if (stainedGlass != null) {
            fillImageInfo(stainedGlass);
        }
    }

    private void fillImageInfo(StainedGlass stainedGlass) {
        String image = stainedGlass.getImage();
        String artist = stainedGlass.getArtist();
        String yearBirth = stainedGlass.getYearBirth();
        String yearPassing = stainedGlass.getYearPassing();
        String artistRef = stainedGlass.getArtistRef();
        String glassDate = stainedGlass.getGlassDate();
        String dateRef = stainedGlass.getDateRef();
        String iconography = stainedGlass.getIconography();
        String churchName = stainedGlass.getChurchName();
        String url = stainedGlass.getUrl();

        stainedGlassImageView.setImageBitmap(getBitmapFromBase64(image));
        artistTextView.setText(getString(R.string.artist_label, artist));
        yearBirthTextView.setText(getString(R.string.year_birth_label, yearBirth));
        yearPassingTextView.setText(getString(R.string.year_passing_label, yearPassing));
        artistReferenceTextView.setText(getString(R.string.artist_reference_label, artistRef));
        glassDateTextView.setText(getString(R.string.glass_date_label, glassDate));
        dateReferenceTextView.setText(getString(R.string.date_reference_label, dateRef));
        iconographyTextView.setText(getString(R.string.iconography_label, iconography));
        churchNameTextView.setText(getString(R.string.church_name_label, churchName));
        urlTextView.setText(getString(R.string.url_label, url));
    }
}