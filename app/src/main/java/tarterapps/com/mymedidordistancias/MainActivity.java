package tarterapps.com.mymedidordistancias;

import android.content.Context;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {


    private static double MAD_LAT =40.406953;
    private static double MAD_LON =-3.6912509;

    private static double BCN_LAT =41.3850639;
    private static double BCN_LON =2.17340349;

    private static double VAL_LAT =39.4699075;
    private static double VAL_LON =-0.376288;

    private static double SEV_LAT =37.3890924;
    private static double SEV_LON =-5.9844588;

    private static double ZGZ_LAT =41.6488226;
    private static double ZGZ_LON =-0.8890853;



    Button btnShowLocation;
    Button btnResetValues;
    TextView myLongitud;
    TextView myLatitud;

    TextView distMadrid;
    TextView distBarcelona;
    TextView distValencia;
    TextView distSevilla;
    TextView distZaragoza;




    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnResetValues = (Button) findViewById(R.id.buttonReset);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(getBaseContext());

                // check if GPS enabled
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    String latS = String.valueOf(latitude);
                    String lonS = String.valueOf(longitude);

                    myLongitud =  (TextView)findViewById(R.id.textViewLonResul);
                    myLatitud  = (TextView)findViewById(R.id.textViewLatResul);
                    myLongitud.setText(lonS);
                    myLatitud.setText(latS);

                    Location myLocation = gps.getLocation();

                    Location locationMad = new Location("Madrid");
                    Location locationBcn = new Location("Barcelona");
                    Location locationVal = new Location("Valencia");
                    Location locationSev = new Location("Sevilla");
                    Location locationZgz = new Location("Zaragoza");

                    locationMad.setLatitude(MAD_LAT);
                    locationMad.setLongitude(MAD_LON);

                    locationBcn.setLatitude(BCN_LAT);
                    locationBcn.setLongitude(BCN_LON);

                    locationVal.setLatitude(VAL_LAT);
                    locationVal.setLongitude(VAL_LON);

                    locationSev.setLatitude(SEV_LAT);
                    locationSev.setLongitude(SEV_LON);

                    locationZgz.setLatitude(ZGZ_LAT);
                    locationZgz.setLongitude(ZGZ_LON);


                    float distanceMad = myLocation.distanceTo(locationMad)/1000;
                    float distanceBcn = myLocation.distanceTo(locationBcn)/1000;
                    float distanceVal = myLocation.distanceTo(locationVal)/1000;
                    float distanceSev = myLocation.distanceTo(locationSev)/1000;
                    float distanceZgz = myLocation.distanceTo(locationZgz)/1000;


                    DecimalFormat df = new DecimalFormat("###.#");
                    df.format(distanceMad);


                    String distMadS=   df.format(distanceMad);
                    String distBcnS=   df.format(distanceBcn);
                    String distValS=   df.format(distanceVal);
                    String distSevS=   df.format(distanceSev);
                    String distZgzS=   df.format(distanceZgz);


                    ((TextView)findViewById(R.id.textViewMad)).setText(distMadS+ " km");
                    ((TextView)findViewById(R.id.textView2Bcn)).setText(distBcnS+" km");
                    ((TextView)findViewById(R.id.textViewVal)).setText(distValS+ " km");
                    ((TextView)findViewById(R.id.textViewSev)).setText(distSevS+ " km");
                    ((TextView)findViewById(R.id.textViewZgz)).setText(distZgzS+ " km");


                } else {

                    gps.showSettingsAlert();
                }

            }
        });


        // show location button click event
        btnResetValues.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ((TextView)findViewById(R.id.textViewMad)).setText("");
                ((TextView)findViewById(R.id.textView2Bcn)).setText("");
                ((TextView)findViewById(R.id.textViewVal)).setText("");
                ((TextView)findViewById(R.id.textViewSev)).setText("");
                ((TextView)findViewById(R.id.textViewZgz)).setText("");

                ((TextView)findViewById(R.id.textViewLonResul)).setText("");
                ((TextView)findViewById(R.id.textViewLatResul)).setText("");

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
