package system.itnt.com.barcodescanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
	Context  context;
	TextView barcodeText,barcodeType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		context=this;
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		barcodeText=(TextView)findViewById(R.id.barcodeText);
		barcodeType=(TextView)findViewById(R.id.barcodeType);
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
//				IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
//				integrator.setOrientationLocked(false);
//				integrator.setCaptureActivity(SmallCaptureActivity.class);
//				integrator.setBeepEnabled(true);
//				integrator.setCameraId(1);
//				integrator.setBarcodeImageEnabled(true);
////				integrator.setTimeout(5000);
//				integrator.initiateScan();
				new IntentIntegrator(MainActivity.this).initiateScan();
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
		if ( id == R.id.action_settings ) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	// Get the results:
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if(result != null) {
			if(result.getContents() == null) {
				Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
				barcodeText.setText(result.getContents());
				barcodeType.setText(result.getFormatName());
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}
