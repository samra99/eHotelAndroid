package com.example.hotelapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelapplication.fragments.HelpFragment;
import com.example.hotelapplication.fragments.RezervacijeListeFragment;
import com.example.hotelapplication.fragments.SobeListFragment;
import com.example.hotelapplication.helper.MyConfig;
import com.example.hotelapplication.helper.MyFragmentUtils;
import com.example.hotelapplication.helper.MyGson;
import com.example.hotelapplication.helper.MySession;
import com.example.hotelapplication.helper.MyUrlConnection;
import com.example.hotelapplication.model.AutentifikacijaLoginPostVM;
import com.example.hotelapplication.model.AutentifikacijaResultVM;

public class GlavniActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        AutentifikacijaResultVM korisnik = MySession.getKorisnik();

        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView imeIprezimeTV = headerView.findViewById(R.id.txtMail);
        TextView emailTV = headerView.findViewById(R.id.txtImePrezime);
        imeIprezimeTV.setText(korisnik.username);
        emailTV.setText(korisnik.mail);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        selectDrawerItem(menuItem);

                        // close drawer when item is tapped


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        MyFragmentUtils.openAsReplace(this, R.id.mjestoFragment, HelpFragment.newInstance());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.glavni, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    public boolean selectDrawerItem(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sobe) {
            MyFragmentUtils.openAsReplace(this, R.id.mjestoFragment, SobeListFragment.newInstance());
        } else if (id == R.id.nav_reservation) {
            MyFragmentUtils.openAsReplace(this, R.id.mjestoFragment, RezervacijeListeFragment.newInstance());

        } else if (id == R.id.nav_Home) {
            MyFragmentUtils.openAsReplace(this, R.id.mjestoFragment, HelpFragment.newInstance());

        } else if (id == R.id.nav_odjava) {
            logOut();
            startActivity(new Intent(this, LoginActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        item.setChecked(true);
        // Set action bar title
        toolbar.setTitle(item.getTitle());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {
        new AsyncTask<Void, Void, AutentifikacijaResultVM>() {

            @Override
            protected void onPreExecute() {
            }


            @Override
            protected AutentifikacijaResultVM doInBackground(Void... voids) {


                String strJson = MyUrlConnection.Get(MyConfig.baseUrl + "api/Autentifikacija/Logout");
                AutentifikacijaResultVM x = MyGson.build().fromJson(strJson, AutentifikacijaResultVM.class);
                return x;
            }

            @Override
            protected void onPostExecute(AutentifikacijaResultVM x) {
                MySession.setKorisnik(null);

                Toast.makeText(GlavniActivity.this, "LogOut", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
