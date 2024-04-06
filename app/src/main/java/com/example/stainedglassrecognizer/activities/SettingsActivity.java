package com.example.stainedglassrecognizer.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import com.example.stainedglassrecognizer.R;
import com.example.stainedglassrecognizer.models.Language;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    private TextInputLayout languageTextInputLayout;
    private MaterialButton applyButton;
    private String language = AppCompatDelegate.getApplicationLocales().toLanguageTags();
    private List<Language> languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        languages = Arrays.asList(
                new Language(getString(R.string.english_item), "en"),
                new Language(getString(R.string.french_item), "fr"),
                new Language(getString(R.string.portuguese_item), "pt"),
                new Language(getString(R.string.spanish_item), "es")
        );

        languageTextInputLayout = findViewById(R.id.language_text_input_layout);
        applyButton = findViewById(R.id.apply_button);

        setLanguagePicker();

        applyButton.setOnClickListener(view -> applySettings());
    }

    private void setLanguagePicker() {
        ArrayAdapter<Language> adapter = new ArrayAdapter<>(this, R.layout.list_item, languages);
        AutoCompleteTextView languageAutoCompleteTextView = (AutoCompleteTextView) languageTextInputLayout.getEditText();

        if (languageAutoCompleteTextView != null) {
            languageAutoCompleteTextView.setAdapter(adapter);
            languageAutoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> language = languages.get(i).getCode());
            languageAutoCompleteTextView.setText(getCurrentLanguage(), false);
        }
    }

    @NonNull
    private String getCurrentLanguage() {
        for (Language item : languages) {
            if (item.getCode().equals(language)) {
                return item.getName();
            }
        }

        return getString(R.string.english_item);
    }

    private void applySettings() {
        LocaleListCompat appLocale = LocaleListCompat.forLanguageTags(language);
        AppCompatDelegate.setApplicationLocales(appLocale);
        finish();
    }
}