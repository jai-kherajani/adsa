package com.example.adsa;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .enableDarkMode(false)
                .setImage(R.drawable.appdirect)
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("Connect with us")
                .addEmail("support@appdirect.com.")
                .addWebsite("https://www.appdirect.com/")
                .addFacebook("https://www.facebook.com/AppDirect/")
                .addTwitter("https://twitter.com/appdirect")
                .addYoutube("https://www.youtube.com/channel/UCMxu2ojBEnZgvFxvKexAjsg")
                .addInstagram("https://www.instagram.com/appdirect")
                .addGitHub("https://github.com/jai-kherajani/adsa")
                .addItem(getCopyRightsElement())
                .setDescription("Created with ❤️ by PSDS India team.")
                .create();
        return aboutPage;
    }

    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = "Copyrights";
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.copyright);
        copyRightsElement.setAutoApplyIconTint(true);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }
}
