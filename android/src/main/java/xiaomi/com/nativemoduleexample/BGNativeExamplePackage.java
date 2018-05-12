package xiaomi.com.nativemoduleexample;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by xieyusheng on 2018/5/12.
 */

public class BGNativeExamplePackage implements ReactPackage {
    //是用来添加原生模块的
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Arrays.asList(new NativeModule[]{
                new BGNativeExampleModule(reactContext),
        });
    }
    //是用来添加原生的UI组件；
    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
//        return null;
        return Collections.emptyList();
    }
}
