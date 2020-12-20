package com.example.adsa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Toolbar myToolbar = findViewById(R.id.coming_soon_toolbar);
        setSupportActionBar(myToolbar);

        Element adsElement = new Element();
        adsElement.setTitle("One Team One Dream!!");


        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .enableDarkMode(false)
                .setImage(R.drawable.appdirect)
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("support@appdirect.com.")
                .addWebsite("https://www.appdirect.com/")
                .addFacebook("https://www.facebook.com/AppDirect/")
                .addTwitter("https://twitter.com/appdirect")
                .addYoutube("https://www.youtube.com/channel/UCMxu2ojBEnZgvFxvKexAjsg")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addInstagram("https://www.instagram.com/appdirect")
                .addGitHub("https://github.com/jai-kherajani/adsa")
                .addItem(getCopyRightsElement())
                .setDescription("Nicolas founded AppDirect in 2009 with Daniel Saks, and serves as AppDirect's Chairman and Co-CEO. Nicolas is focused on the evolution of AppDirect's product, investor relations, and overseeing the implementation of new marketplaces.")
                .create();

        setContentView(aboutPage);
    }


    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights =  "Copyrights";
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.copyright);
        copyRightsElement.setAutoApplyIconTint(true);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutActivity.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}