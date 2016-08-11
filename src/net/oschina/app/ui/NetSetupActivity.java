package net.oschina.app.ui;

import butterknife.ButterKnife;
import butterknife.InjectView;
import net.oschina.app.AppContext;
import net.oschina.app.R;
import net.oschina.app.api.ApiHttpClient;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NetSetupActivity extends Activity {

    @InjectView(R.id.et_path)
	EditText et_path;
    @InjectView(R.id.et_port)
	EditText et_port;
	private AppContext appContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_net_setup);
		
		ButterKnife.inject(this);
		
		appContext = AppContext.getInstance();
		
		et_path.setText(appContext.getProperty("path"));
		et_port.setText(appContext.getProperty("port"));
		
	}
	
	public void save(View view){
		String path = et_path.getText().toString();
		String port = et_port.getText().toString();
		
		appContext.setProperty("path", path);
		appContext.setProperty("port", port);
		
		ApiHttpClient.HOST_LOCAL = path;
		ApiHttpClient.API_URL_LOCAL = "http://"+path+":"+port+"/%s";
		
		appContext.showToast("保存成功");
	}
	
	public void clear(View view){
		AppContext.getInstance().setProperty("path", "");
		AppContext.getInstance().setProperty("port", "");

		ApiHttpClient.HOST_LOCAL = "192.168.1.107";
		ApiHttpClient.API_URL_LOCAL = "http://192.168.1.107:8080/%s";
		
		et_path.setText("");
		et_port.setText("");
		
		appContext.showToast("清除成功");
	}
	
}
