package introduction.android.gpsLocationin;

/*
 * ������GPSLocation�Ĺ�����ʹ��GPSʵʱ��λ,ʵʱ��ʾ�ֻ��ľ�γ��
 */
import introduction.android.gpsLocation.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager; //
import android.location.Location; //
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn_listen;
	private TextView tv_01, tv_02;
	LocationManager lm; //
	Location loc; //

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_listen = (Button) findViewById(R.id.btn_listen);
		tv_01 = (TextView) findViewById(R.id.tv_01);
		tv_02 = (TextView) findViewById(R.id.tv_02);
		// lm = (LocationManager)
		// this.getSystemService(Context.LOCATION_SERVICE); //
		// // ���GPS״̬���Ƿ�����
		// if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) { // ��δ��GPS
		// Toast.makeText(MainActivity.this, "�뿪��GPS����", Toast.LENGTH_LONG)
		// .show();
		// Intent myintent = new Intent(
		// Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		// startActivity(myintent); // �����ֻ������ó���
		// }
		//

		//
		// btn_listen.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
		// new MyLocationListener());
		// }
		// });
		openGPSSettings();
		getLocation();
	}

	private void openGPSSettings() {
		LocationManager alm = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
			Toast.makeText(this, "GPSģ������", Toast.LENGTH_SHORT).show();
			return;
		}
		Toast.makeText(this, "�뿪��GPS��", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
		startActivityForResult(intent, 0); // ��Ϊ������ɺ󷵻ص���ȡ����
	}

	private void getLocation() {
		// ��ȡλ�ù������
		LocationManager locationManager;
		String serviceName = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) this.getSystemService(serviceName);
		// ���ҵ�������Ϣ
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE); // �߾���
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW); // �͹���
		String provider = locationManager.getBestProvider(criteria, true); // ��ȡGPS��Ϣ
		Location location = locationManager.getLastKnownLocation(provider); // ͨ��GPS��ȡλ��
		updateToNewLocation(location);
		// ���ü���*�����Զ����µ���Сʱ��Ϊ���N��(1��Ϊ1*1000������д��ҪΪ�˷���)����Сλ�Ʊ仯����N��
		locationManager.requestLocationUpdates(provider, 100 * 1000, 500,
				new MyLocationListener());
	}

	private void updateToNewLocation(Location location) {
		if (location != null) {
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			tv_01.setText("���ȣ�" + latitude + "γ��" + latitude);
		} else {
			tv_01.setText("�޷���ȡ");
		}
	}

	class MyLocationListener implements LocationListener { // λ�ü���������Ϊ��������
		@Override
		public void onLocationChanged(Location loc) {
			// // TODO Auto-generated method stub
			// tv_01.setText("���ȣ�" + loc.getLongitude());
			// tv_02.setText("γ�ȣ�" + loc.getLatitude());

		}

		@Override
		public void onProviderDisabled(String provider) {
			// ��provider���û��ر�ʱ����
			Log.i("GpsLocation", "provider���رգ�");
		}

		@Override
		public void onProviderEnabled(String provider) {
			// ��provider���û����������
			Log.i("GpsLocation", "provider��������");
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// ��provider��״̬��OUT_OF_SERVICE��TEMPORARILY_UNAVAILABLE��AVAILABLE֮�䷢���仯ʱ����
			Log.i("GpsLocation", "provider״̬�����ı䣡");
		}
	}
}