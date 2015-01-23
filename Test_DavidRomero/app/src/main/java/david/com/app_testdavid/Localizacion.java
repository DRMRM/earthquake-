package david.com.app_testdavid;



import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;




/*Clase que se encarga de todas las funciones de localizacion*/
public class Localizacion extends ActionBarActivity implements LocationListener {

	private static LocationManager _locationManager;
	private Location _userLocation;
	private GoogleMap _mapa;

    double lat, longi;
    String title,desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_localization);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Localization Earthquake");




        try {
            if (_mapa == null) {
                _mapa = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            _mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);




        } catch (Exception e) {
            e.printStackTrace();
        }



        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){

            lat= Double.parseDouble(bundle.getString("lat"));
            longi=Double.parseDouble(bundle.getString("longi"));
            title=bundle.getString("title");
            desc=bundle.getString("desc");


            centraMapa();

            showmarker(title, desc, lat, longi);
            Log.e("Latitud    longitud", "Latitud :"+ lat + "Longitud :" +longi);
        }else {
setUserLocation();
        }





	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onPause() {
		super.onPause();


	}

	private void setUserLocation() {
		// TODO: Siempre regresa Nulo
		/*
		 * try{ _locationManager = (LocationManager)
		 * this.getActivity().getSystemService(Context.LOCATION_SERVICE); if
		 * (_locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
		 * _locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		 * MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this); if
		 * (_locationManager != null){ _userLocation =
		 * _locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		 * } } else {
		 * _locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER
		 * , MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this); if
		 * (_locationManager != null){ _userLocation =
		 * _locationManager.getLastKnownLocation
		 * (LocationManager.NETWORK_PROVIDER); } } }catch(Exception ex){
		 * ex.printStackTrace(); }
		 */


		// flag for GPS status
		boolean isGPSEnabled = false;
		// flag for network status
		boolean isNetworkEnabled = false;
		boolean canGetLocation = false;

		Location location = null; // location
		double latitude; // latitude
		double longitude; // longitude

		// The minimum distance to change Updates in meters
		final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
		// The minimum time between updates in milliseconds
		final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

		// Declaring a Location Manager
		LocationManager locationManager;

		try {
			_locationManager = (LocationManager) Localizacion.this
					.getSystemService(Context.LOCATION_SERVICE);

			isGPSEnabled = _locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled = _locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				Toast.makeText(Localizacion.this,
						"Por favor encienda su GPS.", Toast.LENGTH_LONG).show();
				// Log.d("Localizacion", "No hay GPS");
			} else {
				canGetLocation = true;
				// First get location from Network Provider
				if (isNetworkEnabled) {
					_locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					// Log.d("Network", "Network");
					if (_locationManager != null) {
						location = _locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							// latitude = location.getLatitude();
							// longitude = location.getLongitude();
							centraMapa(location);
						} else {
							Toast.makeText(Localizacion.this,
									"Por favor encienda su GPS.",
									Toast.LENGTH_LONG).show();

						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						_locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						// Log.d("GPS Enabled", "GPS Enabled");
						if (_locationManager != null) {
							location = _locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								// latitude = location.getLatitude();
								// longitude = location.getLongitude();
								centraMapa(location);
							} else {
								Toast.makeText(Localizacion.this,
										"Por favor encienda su GPS.",
										Toast.LENGTH_LONG).show();
							}
						}
					}
				}
			}

		} catch (Exception e) { e.printStackTrace();}
		// _userLocation = _mapa.getMyLocation();
	}


	//************Muestra los marcadores que se visualizan en los mapas del dispositivo***************///

	private void showmarker(String title, String desc, double a1,double a2) {
		_mapa.addMarker(new MarkerOptions()
		.position(new LatLng(a1, a2))
		.title(title)
		.snippet(desc)
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_map)));

        LatLng posicion = new LatLng(a1, a2);
        _mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 4.5f));

	}









	private void centraMapaCompleto() {
		LatLng posicion = new LatLng(20.683421, -103.39448);

		_mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 3.5f));
	}

	private void centraMapa(Location _userLocation) {

		LatLng posicion = new LatLng(_userLocation.getLatitude(),
				_userLocation.getLongitude());
		_mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 13));
	}


	//*********OBTENER LA UBICACION ACTUAL DEL DISPOSITIVO***************//
	private void centraMapa() {

		try {
			LatLng posicion = new LatLng(_userLocation.getLatitude(),
					_userLocation.getLongitude());
			_mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 13));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}





	/**
	 * Métodos del Location Listener
	 */
	@Override
	public void onLocationChanged(Location location) {
		_userLocation = location;
		_locationManager.removeUpdates(this);
		centraMapa(location);
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}
}
