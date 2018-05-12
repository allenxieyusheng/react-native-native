package xiaomi.com.nativemoduleexample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

/**
 * Created by xieyusheng on 2018/5/12.
 * 参考文档：http://www.devio.org/2016/09/29/React-Native原生模块向JS传递数据的几种方式/
 * http://www.liuchungui.com/blog/2016/05/08/reactnativezhi-yuan-sheng-mo-kuai-kai-fa-bing-fa-bu-androidpian/
 */

public class BGNativeExampleModule extends ReactContextBaseJavaModule {
    public BGNativeExampleModule(final ReactApplicationContext reactContext) {
        super(reactContext);
    }
    @Override
    public String getName() {
        return "BGNativeModuleExample";
    }
    @ReactMethod
    public void testToast(String message) {
//        Log.i(TAG, name);
//        Log.i(TAG, info.toString());
        //js端传来的数据
        Toast.makeText(getReactApplicationContext(), message,Toast.LENGTH_SHORT).show();
    }
    //1. 采用回调函数将数据传递给js端
    @ReactMethod
    public void getNativeClass(Callback callback) {
        callback.invoke("原生放回来的数据");
    }
    //2.采用Promise，返回数据给js端
    @ReactMethod
    public void testPromises(Boolean isResolve, Promise promise) {
        if(isResolve) {
            promise.resolve(isResolve.toString());
        }
        else {
            promise.reject(isResolve.toString());
        }
    }
    //3. 导出常量给js端
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("BGModuleName", "NBA");
        constants.put("c","CBA");
        return constants;
    }
   //3.通过事件监听
   public void onHandleResult(String barcodeData) {
       WritableMap params = Arguments.createMap();
       params.putString("result", barcodeData);
       sendEvent(getReactApplicationContext(), "onScanningResult", params);
   }
    private void sendEvent(ReactContext reactContext,String eventName, @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}
