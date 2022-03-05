import android.app.Application;
import android.content.res.Configuration;

import com.instabug.library.Instabug;

public class MyCustomApplication extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();
        new Instabug.Builder(this, "APP_TOKEN").build();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
    }

}
