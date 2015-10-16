package in.co.foodamigo.shipment.module.app.view.component;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.common.io.ByteStreams;
import com.skobbler.ngx.SKDeveloperKeyException;
import com.skobbler.ngx.SKMaps;
import com.skobbler.ngx.SKMapsInitSettings;
import com.skobbler.ngx.SKPrepareMapTextureListener;
import com.skobbler.ngx.SKPrepareMapTextureThread;
import com.skobbler.ngx.map.SKMapViewStyle;
import com.skobbler.ngx.util.SKLogging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.co.foodamigo.shipment.R;

public class SplashActivity extends AppCompatActivity implements SKPrepareMapTextureListener {

    private String mapResourcesDirPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SKLogging.enableLogs(true);

        mapResourcesDirPath = getExternalFilesDir(null).toString() + "/" + "SKMaps/";
        new SKPrepareMapTextureThread(this, mapResourcesDirPath, "SKMaps.zip", this).start();
        copyOtherResources();
        prepareMapCreatorFile();
    }

    private void copyOtherResources() {
        new Thread() {

            public void run() {
                try {
                    String tracksPath = mapResourcesDirPath + "GPXTracks";
                    File tracksDir = new File(tracksPath);
                    if (!tracksDir.exists()) {
                        tracksDir.mkdirs();
                    }
                    copyAssetsToFolder(getAssets(), "GPXTracks", mapResourcesDirPath + "GPXTracks");

                    String imagesPath = mapResourcesDirPath + "images";
                    File imagesDir = new File(imagesPath);
                    if (!imagesDir.exists()) {
                        imagesDir.mkdirs();
                    }
                    copyAssetsToFolder(getAssets(), "images", mapResourcesDirPath + "images");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * Copies the map creator file and logFile from assets to a storage.
     */
    private void prepareMapCreatorFile() {
        final Thread prepareGPXFileThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

                    final String mapCreatorFolderPath = mapResourcesDirPath + "MapCreator";
                    final File mapCreatorFolder = new File(mapCreatorFolderPath);
                    // create the folder where you want to copy the json file
                    if (!mapCreatorFolder.exists()) {
                        mapCreatorFolder.mkdirs();
                    }
                    copyAsset(getAssets(), "MapCreator", mapCreatorFolderPath, "mapcreatorFile.json");
                    // Copies the log file from assets to a storage.
                    final String logFolderPath = mapResourcesDirPath + "logFile";
                    final File logFolder = new File(logFolderPath);
                    if (!logFolder.exists()) {
                        logFolder.mkdirs();
                    }
                    copyAsset(getAssets(), "logFile", logFolderPath, "Seattle.log");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        prepareGPXFileThread.start();
    }


    @Override
    public void onMapTexturesPrepared(boolean b) {
        Toast.makeText(SplashActivity.this, "Map resources were copied", Toast.LENGTH_SHORT).show();

        if (initializeLibrary(this)) {
            finish();
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        }
    }

    public static void copyAssetsToFolder(AssetManager assetManager, String sourceFolder, String destinationFolder)
            throws IOException {
        final String[] assets = assetManager.list(sourceFolder);

        final File destFolderFile = new File(destinationFolder);
        if (!destFolderFile.exists()) {
            destFolderFile.mkdirs();
        }
        copyAsset(assetManager, sourceFolder, destinationFolder, assets);
    }

    /**
     * Copies files from assets to destination folder
     *
     * @param assetManager
     * @param sourceFolder
     * @param assetsNames
     * @throws IOException
     */
    public static void copyAsset(AssetManager assetManager, String sourceFolder, String destinationFolder,
                                 String... assetsNames) throws IOException {

        for (String assetName : assetsNames) {
            OutputStream destinationStream = new FileOutputStream(new File(destinationFolder + "/" + assetName));
            String[] files = assetManager.list(sourceFolder + "/" + assetName);
            if (files == null || files.length == 0) {

                InputStream asset = assetManager.open(sourceFolder + "/" + assetName);
                try {
                    ByteStreams.copy(asset, destinationStream);
                } finally {
                    asset.close();
                    destinationStream.close();
                }
            }
        }
    }

    /**
     * Initializes the SKMaps framework
     */
    public boolean initializeLibrary(final Activity context) {
        SKLogging.enableLogs(true);

        // get object holding map initialization settings
        SKMapsInitSettings initMapSettings = new SKMapsInitSettings();

        // set path to map resources and initial map style
        initMapSettings.setMapResourcesPaths(mapResourcesDirPath,
                new SKMapViewStyle(mapResourcesDirPath + "daystyle/", "daystyle.json"));

        /*final SKAdvisorSettings advisorSettings = initMapSettings.getAdvisorSettings();
        advisorSettings.setAdvisorConfigPath(mapResourcesDirPath + "/Advisor");
        advisorSettings.setResourcePath(mapResourcesDirPath + "/Advisor/Languages");
        advisorSettings.setLanguage(SKAdvisorSettings.SKAdvisorLanguage.LANGUAGE_EN);
        advisorSettings.setAdvisorVoice("en");
        initMapSettings.setAdvisorSettings(advisorSettings);*/

        // EXAMPLE OF ADDING PREINSTALLED MAPS
        initMapSettings.setPreinstalledMapsPath(mapResourcesDirPath + "/PreinstalledMaps");
        initMapSettings.setConnectivityMode(SKMaps.CONNECTIVITY_MODE_OFFLINE);
        initMapSettings.setMapDetailLevel(SKMapsInitSettings.SK_MAP_DETAIL_FULL);
        // Example of setting light maps
        // initMapSettings.setMapDetailLevel(SKMapsInitSettings.SK_MAP_DETAIL_LIGHT);
        // initialize map using the settings object
        try {
            SKMaps.getInstance().initializeSKMaps(context, initMapSettings);
            return true;
        } catch (SKDeveloperKeyException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
